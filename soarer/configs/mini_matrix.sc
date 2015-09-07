# Matrix setup for Soarer's Mini
# http://deskthority.net/workshop-f7/a-58-key-mini-keyboard-with-a-missing-column-t1197.html

led caps -PC6
led scroll -PC7

matrix
	scanrate 1
	debounce 5
	blocking 0

	sense			PB0			PB1	PB2			PB3	PD0	PD1			PD2			PD3
	strobe	PF0		ESC			2	4			5	7	9			MINUS		LCTRL
	strobe	PF1		1			3	UNASSIGNED	6	8	0			BACKSPACE	LGUI
	strobe	PF4		TAB			W	R			T	U	O			LEFT_BRACE	LALT
	strobe	PF5		Q			E	BACKSPACE	Y	I	P			RIGHT_BRACE	UNASSIGNED
	strobe	PF6		CAPS_LOCK	S	F			G	J	L			ENTER		RALT
	strobe	PF7		A			D	SPACE		H	K	SEMICOLON	UNASSIGNED	MENU
	strobe	PB6		LSHIFT		Z	C			V	N	COMMA		FN2			RCTRL
	strobe	PB7		FN1			X	UNASSIGNED	B	M	PERIOD		RSHIFT		UNASSIGNED
end
