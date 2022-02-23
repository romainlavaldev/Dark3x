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

const char* CLEAR_CMD = "module=graph&commande=clearScreen";
const char* COLOR_CMD = "module=graph&commande=setColor&c=%d";
const char* POINT_CMD = "module=graph&commande=drawPoint&x=%d&y=%d";
const char* LINE_CMD = "module=graph&commande=drawLine&x1=%d&y1=%d&x2=%d&y2=%d";
const char* CIRCLE_CMD = "module=graph&commande=drawCircle&x=%d&y=%d&r=%d";

const char* TURTLE_SHOW_CMD = "module=graph&commande=turtleShow";
const char* TURTLE_HIDE_CMD = "module=graph&commande=turtleHide";
const char* TURTLE_GOTO_CMD = "module=graph&commande=turtleGoto&x=%d&y=%d";
const char* TURTLE_RESET_CMD = "module=graph&commande=turtleReset";
const char* TURTLE_FORWARD_CMD = "module=graph&commande=turtleForward&d=%d";
const char* TURTLE_TURNL_CMD = "module=graph&commande=turtleTurnLeft&a=%d";
const char* TURTLE_TURNR_CMD = "module=graph&commande=turtleTurnRight&a=%d";

/*
const int COLOR_WHITE = 0;
const int COLOR_BLACK = 1;
const int COLOR_RED = 2;
const int COLOR_GREEN = 3;
const int COLOR_BLUE = 4;
const int COLOR_CYAN = 5;
const int COLOR_YELLOW = 6;
const int COLOR_ERROR = 7;
*/

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

int clearScreen() {
	return request(CLEAR_CMD);
}

int setColor(int color) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), COLOR_CMD, color);
	return request(buffer);
}

int drawPoint(int x, int y) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), POINT_CMD, x, y);
	return request(buffer);
}

int drawLine(int x1, int y1, int x2, int y2) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), LINE_CMD, x1, y1, x2, y2);
	return request(buffer);
}

int drawCircle(int x, int y, int r) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), CIRCLE_CMD, x, y, r);
	return request(buffer);
}

int turtleShow() {
	return request(TURTLE_SHOW_CMD);
}

int turtleHide() {
	return request(TURTLE_HIDE_CMD);
}

int turtleGoto(int x, int y) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), TURTLE_GOTO_CMD, x, y);
	return request(buffer);
}

int turtleReset() {
	return request(TURTLE_RESET_CMD);
}

int turtleForward(int dist) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), TURTLE_FORWARD_CMD, dist);
	return request(buffer);
}

int turtleTurnLeft(int angle) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), TURTLE_TURNL_CMD, angle);
	return request(buffer);
}

int turtleTurnRight(int angle) {
	char buffer[1024];
	snprintf(buffer, sizeof(buffer), TURTLE_TURNR_CMD, angle);
	return request(buffer);
}
