/*
#####################################################################
##  This file is part of the software CodeLab IDE and Workshop     ##
##  Copyright © Jérôme Lehuen 2020 - Jerome.Lehuen@univ-lemans.fr  ##
#####################################################################
*/

// DO NOT DELETE OR MODIFY THIS FILE !!

#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define SERVER_PORT "8500"
#define BUFSIZE 1024

int _sock_open();
int _sock_send(int sockfd, char* request);
int _error_exit(const char* msg, int errcode);

int request(const char* msg) {
	char buffer[BUFSIZE];
	bzero(buffer, BUFSIZE);
	snprintf(buffer, sizeof(buffer), "%s\n", msg);
	int sockfd = _sock_open(SERVER_PORT);
	int result = _sock_send(sockfd, buffer);
	close(sockfd);
	return result;
}

int _sock_open(const char* port) {
	struct addrinfo hints, *data;
	int sockfd, code;
	memset(&hints, 0, sizeof hints);
	hints.ai_family = AF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;
	getaddrinfo("localhost", port, &hints, &data);
	sockfd = socket(data->ai_family, data->ai_socktype, data->ai_protocol);
	if ((code = connect(sockfd, data->ai_addr, data->ai_addrlen)) < 0)
		_error_exit("Error %d in _sock_open", code);
	return sockfd;
}

int _sock_send(int sockfd, char* request) {
	char buffer[BUFSIZE];
	bzero(buffer, BUFSIZE);
	int code;
	if ((code = send(sockfd, request, strlen(request), 0)) < 0)
		_error_exit("Error %d in _sock_send", code);
	read(sockfd, buffer, BUFSIZE);
	buffer[strlen(buffer)-1] = 0; // strip()
	return atoi(buffer);
}

int _error_exit(const char* msg, int errcode) {
	fprintf(stderr, msg, errcode);
	exit(EXIT_FAILURE);
}
