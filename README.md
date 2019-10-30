# Warehouse-Hiking
> Application with the goal of determining the roads which a basket containing a packet can take in a warehouse, by starting from a fixed starting point and hitting requested stations before being allowed to enter the final destination. 

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
### How it works?

The user enters a basket number for the one they want to determine all the possible roads for.  
Each basket has (*for now*) one packet which has some station requirements it needs to visit before being allowed to check-in to the last destination station of the warehouse.  
The requirements are listed in order, and they should be visited as:

*start station -> required station 1 -> required station 2 -> ... -> required station n -> destination station* (1)  

After visiting one station, going back to the same one is not allowed.
- if there are no possible solutions for the required basket, a message stating so will be displayed;
- in case paths were found, they will be displayed as in (1).

### Files
The files used for setting up the necessary items (baskets, packets and roads) have the following structure:  
- **basket**:	basket-number packet-contained
- **packet**: 	packet-number first-station-required second-station-required ...
- **road**: 	start-station end-station    
  
Example:

| baskets.txt     | packets.txt     | roads.txt     |
|:---------------:|:---------------:|:-------------:|
| 1 50            | 50 79 5         | 0 32          |
| 3 50            | 2 32 0          | 0 79          |
| 4 2             | 51 7 5          | 0 5           | 
| 5 3             | 30 2 49         | 32 7          |
|             	  |                 | 0 32          |
|                 |                 | 0 79          |
|                 |                 | 0 5           | 
|                 |                 | 32 7          |
|                 |                 | 79 5          |
|                 |                 | 5 7           |
|                 |                 | 7 49          |
|                 |                 | 7 79          |
|                 |                 | 49 100        | 
                                
## Screenshots
![Example screenshot](./img/screenshot.png)

## Technologies
* Tech 1 - version 1.0
* Tech 2 - version 2.0
* Tech 3 - version 3.0

## Setup
Describe how to install / setup your local environement / add link to demo version.

## Code Examples
Show examples of usage:
`put-your-code-here`

## Features
List of features ready and TODOs for future development
* Awesome feature 1
* Awesome feature 2
* Awesome feature 3

To-do list:
* Wow improvement to be done 1
* Wow improvement to be done 2

## Status
Project is: _in progress_, _finished_, _no longer continue_ and why?

## Inspiration
Add here credits. Project inspired by..., based on...

## Contact
Soon.
