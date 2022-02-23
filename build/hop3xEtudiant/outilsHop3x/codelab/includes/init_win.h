/*
#####################################################################
##  This file is part of the software CodeLab IDE and Workshop     ##
##  Copyright © Jérôme Lehuen 2020 - Jerome.Lehuen@univ-lemans.fr  ##
#####################################################################
*/

// DO NOT DELETE OR MODIFY THIS FILE !!

#include <winsock2.h>
#include <stdio.h>

#define SERVER_PORT 8500
#define BUFSIZE 1024

#pragma comment(lib, "ws2_32.lib") // Winsock library

int _sock_open();
int _sock_send(int sockfd, char* request);
int _error_exit(const char* msg, int errcode);

int request(const char* msg) {
	char buffer[BUFSIZE];
	snprintf(buffer, sizeof(buffer), "%s\n", msg);
	int sockfd = _sock_open(SERVER_PORT);
	int result = _sock_send(sockfd, buffer);
	closesocket(sockfd);
	WSACleanup();
	return result;
}

int _sock_open(const char* port) {
	WSADATA wsa;
	SOCKET sockfd;

	if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0)
		_error_exit("Error %d in _sock_open", WSAGetLastError());

	if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET)
		_error_exit("Error %d in _sock_open", WSAGetLastError());

	return sockfd;
}

int _sock_send(int sockfd, char* request) {
	struct sockaddr_in server;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");
	server.sin_family = AF_INET;
	server.sin_port = htons(SERVER_PORT);

	if (connect(sockfd, (struct sockaddr*) &server, sizeof(server)) < 0)
		_error_exit("Error %d in _sock_send", WSAGetLastError());

	if (send(sockfd, request, strlen(request), 0) < 0)
		_error_exit("Error %d in _sock_send", WSAGetLastError());

	char buffer[BUFSIZE];
	memset(buffer, 0, BUFSIZE);
	int bytesRead = recv(sockfd, buffer, BUFSIZE, 0);
	buffer[bytesRead] = 0x0;
	return atoi(buffer);
}

int _error_exit(const char* msg, int errcode) {
	fprintf(stderr, msg, errcode);
	exit(EXIT_FAILURE);
}
