/*
#####################################################################
##  This file is part of the software CodeLab IDE and Workshop     ##
##  Copyright © Jérôme Lehuen 2020 - Jerome.Lehuen@univ-lemans.fr  ##
#####################################################################
*/

// DO NOT DELETE OR MODIFY THIS FILE !!

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <unistd.h>
#include <time.h>
#include <math.h>

#ifdef _WIN32
#include <windows.h>
#include <sys/timeb.h>
#include <limits.h>
#endif

#define string char*

const char* PRINTSTR_CMD = "module=inout&commande=printStr&value=%s";
const char* PRINTINT_CMD = "module=inout&commande=printInt&value=%d";
const char* PRINTFLT_CMD = "module=inout&commande=printFloat&value=%f";
const char* GETNUMPAD_CMD = "module=inout&commande=getNumPadValue";
const char* PLAYTONE_CMD = "module=audio&commande=playTone&freq=%d&time=%d&ampl=%d";

void strreplace(char* src, char* str, char* rep) {
	char* p = strstr(src, str);
	do {
		if (p) {
			char buf[1024];
			memset(buf, '\0', sizeof buf);
			if (src == p) {
				strcpy(buf, rep);
				strcat(buf, p+strlen(str));
			} else {
				strncpy(buf, src, strlen(src)-strlen(p));
				strcat(buf, rep);
				strcat(buf, p+strlen(str));
			}
			memset(src, '\0', strlen(src));
			strcpy(src, buf);
		}

	} while (p && (p = strstr(src, str)));
}

// --------------------------------------------------------------
// Entrées et sorties

int printStr(char* chaine) {
	char buffer[1024];
	char texte[1024];
	strcpy(texte,chaine);
	strreplace(texte, "\n", "<br/>");
	strreplace(texte, "\t", "<tab/>");
	strreplace(texte, "&", "<and/>");
	strreplace(texte, "=", "<eq/>");
	snprintf(buffer, sizeof(texte), PRINTSTR_CMD, texte);
	return request(buffer);
}

int printFormat(char* format, ...) {
	char *traverse;
	int valeurEntiere;
	long valeurLong;
	double valeurFlottante;
	char* valeurString;
	char dest[1024];
	char temp[80];

	va_list arg;
	va_start(arg, format);

	dest[0] = '\0';
	for (traverse = format ; *traverse ; traverse++) {
		switch (*traverse) {
			case '\n':
				strcat(dest, "<br/>"); break;
			case '\t':
				strcat(dest, "<tab/>"); break;
			case '=':
				strcat(dest, "<eq/>"); break;
			case '&':
				strcat(dest, "<and/>"); break;
			case '%':
				traverse++;
				switch (*traverse) {
					case 'i':
					case 'd':
						valeurEntiere = va_arg(arg, int);
						sprintf(temp, "%d", valeurEntiere);
						strcat(dest, temp);
						break;
					case 'l':
						valeurLong = va_arg(arg, long);
						sprintf(temp, "%ld", valeurLong);
						strcat(dest, temp);
						break;
					case 'f':
						valeurFlottante = va_arg(arg, double);
						sprintf(temp, "%f", valeurFlottante);
						strcat(dest, temp);
						break;
					case 's':
						valeurString = va_arg(arg, char*);
						strcat(dest, valeurString);
				}
				break;
			default:
				strncat(dest, traverse, 1);
		}

	}
	va_end(arg);
	return printStr(dest);
}

int printStrLn(char* chaine){
	return printFormat("%s\n", chaine) ;
}

int printInt(int entier) {
	return printFormat("%d", entier);
}

int printIntLn(int entier) {
	return printFormat("%d\n", entier);
}

int printLong(long entier) {
	return printFormat("%l", entier);
}

int printLongLn(long entier) {
	return printFormat("%l\n", entier);
}

int printFloat(float flottant) {
	return printFormat("%f", flottant);
}

int printFloatLn(float reel) {
	return printFormat("%f\n", reel);
}

int getNumPadValue() {
	return request(GETNUMPAD_CMD);
}

int newline() {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), PRINTSTR_CMD, "<br/>");
	return request(buffer);
}

// --------------------------------------------------------------
// Temporisation en millisecondes

#ifdef _WIN32
void waitFor(int ms) {
	Sleep(ms);
}
#else
void waitFor(int ms) {
	usleep(ms * 1000);
}
#endif

// --------------------------------------------------------------
// Temps système Epoch

#ifdef _WIN32
long systemTime() {
	struct timeb tmb;
	ftime(&tmb);
	// Le modulo est pour éviter le dépassement de capacité
	// lors de la conversion des uint64_t vers les long
	long MAX = LONG_MAX / 1000;
	return tmb.time % MAX * 1000 + tmb.millitm;
}
#else
long systemTime() {
	struct timespec ts;
	clock_gettime(CLOCK_REALTIME, &ts);
	return ts.tv_sec * 1000 + lround(ts.tv_nsec / 1.0e6);
}
#endif

// --------------------------------------------------------------
// Nombre entier aléatoire

void initRandom() {
	srand(time(NULL));
}

int randomInt(int max) {
	return rand() % max;
}

// --------------------------------------------------------------
// Sortie audio

const int ampl = 25; // Amplitude dans [0, 100]

int playTone(int freq, int time) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), PLAYTONE_CMD, freq, time, ampl);
	return request(buffer);
}

