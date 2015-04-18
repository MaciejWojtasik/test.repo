/*
    LESSON 5 - SHADERY, KOLOROWANIE
*/
#version 400 core

in vec3 colour;

out vec4 out_Color;

void main(void){
    out_Color = vec4(colour, 1.0)/1.0;    
}