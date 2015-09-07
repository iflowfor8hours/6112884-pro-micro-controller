# Retrete's matrix config for kevex ray 7000CM
# http://geekhack.org/index.php?topic=17458.msg870218;topicseen#msg870218

led caps -PF5
led num -PF6
led scroll -PC7

matrix
	scanrate 1
	debounce 5
	sense			PB0			PB1		PB2			PB3		PB4		PB5			PB6			PB7
	strobe	PD7		ESC			2		4			5		7		9			MINUS		LCTRL
	strobe	PE0		1			3		UNASSIGNED	6		8		0			BACKSPACE	LGUI
	strobe	PE1		TAB			W		R			T		U		O			LEFT_BRACE	LALT
	strobe	PC0		Q			E		BACKSPACE	Y		I		P			RIGHT_BRACE	UNASSIGNED
	strobe	PC1		CAPS_LOCK	S		F			G		J		L			ENTER		RALT
	strobe	PC2		A			D		SPACE		H		K		SEMICOLON	UNASSIGNED	MENU
	strobe	PC3		UNASSIGNED	Z		C			V		N		COMMA		FN2			RCTRL
	strobe	PC4		FN1			X		UNASSIGNED	B		M		PERIOD		UNASSIGNED	UNASSIGNED
	strobe	PC5		PAD_1		PAD_2	PAD_3		PAD_4	PAD_5	PAD_6		PAD_7		PAD_8
	strobe	PC6		F1			F2		F3			F4		F5		F6			F7			F8
	strobe	PC7		F9			F10		F11			F12		UNASSIGNED	UNASSIGNED	UNASSIGNED	UNASSIGNED	
	unstrobed -PD0 LSHIFT
	unstrobed -PD1 RSHIFT
	unstrobed -PD2 UNASSIGNED
end
