/*
#####################################################################
##  This file is part of the software CodeLab IDE and Workshop     ##
##  Copyright © Jérôme Lehuen 2020 - Jerome.Lehuen@univ-lemans.fr  ##
#####################################################################
*/

// DO NOT DELETE OR MODIFY THIS FILE !!

// --------------------------------------------------------------
// Chargement de la fonction request

#ifdef __APPLE__
#include "init_unix.h"
#elif __linux__
#include "init_unix.h"
#elif _WIN32
#include "init_win.h"
#else
#error "OS not supported"
#endif
