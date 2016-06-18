#version 130

attribute vec3 position;
attribute vec3 color;

out vec3 exColor;

void main()
{
	gl_Position = vec4(position, 1.0);
	exColor = color;
}