#version 130

attribute vec3 position;
attribute vec3 color;

varying vec3 exColor;

uniform mat4 projectionMatrix;
uniform mat4 worldMatrix;

void main()
{
	gl_Position = projectionMatrix * worldMatrix * vec4(position, 1.0);
	exColor = color;
}