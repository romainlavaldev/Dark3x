/*
#####################################################################
##  This file is part of the software CodeLab IDE and Workshop     ##
##  Copyright © Jérôme Lehuen 2020 - Jerome.Lehuen@univ-lemans.fr  ##
#####################################################################
*/

// DO NOT DELETE OR MODIFY THIS FILE !!

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

const char* MOTORON_CMD = "module=robot&robot=lego&commande=motorOn&port=%d&power=%d";
const char* MOTOROFF_CMD = "module=robot&robot=lego&commande=motorOff&port=%d";
const char* GETARRAY_CMD = "module=robot&robot=lego&commande=getSensorArray&port=%d&index=%d";
const char* GETSENSOR_CMD = "module=robot&robot=lego&commande=getSensorValue&port=%d";
const char* GETROTATION_CMD = "module=robot&robot=lego&commande=getMotorRotationCount&port=%d";
const char* RESETROTATION_CMD = "module=robot&robot=lego&commande=resetMotorRotationCount&port=%d";

enum inPort {
	IN_1,
	IN_2,
	IN_3,
	IN_4
};

enum outPort {
	OUT_A,
	OUT_B,
	OUT_C,
	OUT_AB,
	OUT_AC,
	OUT_BC,
	OUT_ABC
};

enum colorCode {
	COLOR_WHITE,
	COLOR_BLACK,
	COLOR_RED,
	COLOR_GREEN,
	COLOR_BLUE,
	COLOR_CYAN,
	COLOR_YELLOW,
	COLOR_ERROR
};

int motorOn(int port, int power) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), MOTORON_CMD, port, power);
	return request(buffer);
}

int motorOff(int port) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), MOTOROFF_CMD, port);
	return request(buffer);
}

int getSensorValue(int port) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), GETSENSOR_CMD, port);
	return request(buffer);
}

int getSensorArray(int port, int array[]) {
	char buffer[1024];
	for (int i = 0; i < 8; i++) {
		snprintf(buffer, sizeof(buffer), GETARRAY_CMD, port, i);
		array[i] = request(buffer);
	}
	return 0;
}

int motorRotationCount(int port) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), GETROTATION_CMD, port);
	return request(buffer);
}

int resetMotorRotationCount(int port) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), RESETROTATION_CMD, port);
	return request(buffer);
}

// --------------------------------------------------------------
// Contrôle du simulateur

const char* SIMUSTART_CMD = "module=robot&commande=simu_start";
const char* SIMUSTOP_CMD = "module=robot&commande=simu_stop";

int simu_start() {
	return request(SIMUSTART_CMD);
}

int simu_stop() {
	return request(SIMUSTOP_CMD);
}
