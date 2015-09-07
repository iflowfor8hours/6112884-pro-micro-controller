# IBM 6112884 Japanese layout with Alps switches
# The CPU was removed from the controller board and replaced with a Teensy, but the 4 to 16 line decoder remains (hence using muxstrobe).
# http://deskthority.net/post114200.html#p114200
# HaaTa's pics of similar keyboard: http://deskthority.net/post87127.html#p87127

matrix
	scanrate 1
	debounce 5
	blocking 0
	muxstrobe_port PF7:4

	#				0			1			2				3			4			5				6			7
	#				8			9			A				B			C			D				E			F

	sense			PB3			PB7			PD0				PD1			PD2			PD3				PC6			PC7
	# 0
	muxstrobe 0		ESC			TAB			Z				S			W			2				UNASSIGNED	UNASSIGNED	
	muxstrobe 1		1			Q			X				D			E			3				UNASSIGNED	UNASSIGNED	
	# 1
	muxstrobe 2		CAPS_LOCK	UNASSIGNED	C				F			R			4				UNASSIGNED	UNASSIGNED	
	muxstrobe 3		A			LSHIFT		V				G			T			5				UNASSIGNED	UNASSIGNED	
	# 2
	muxstrobe 4		LCTRL		LGUI		B				H			Y			6				UNASSIGNED	UNASSIGNED	
	muxstrobe 5		LALT		UNASSIGNED	N				J			U			7				UNASSIGNED	UNASSIGNED	
	# 3
	muxstrobe 6		DOWN		SPACE		M				K			I			8				UNASSIGNED	UNASSIGNED	
	muxstrobe 7		RALT		APP			COMMA			L			O			9				UNASSIGNED	UNASSIGNED	
	# 4
	muxstrobe 8		RCTRL		LEFT		PERIOD			SEMICOLON	P			0				UNASSIGNED	UNASSIGNED	
	muxstrobe 9		RIGHT		PAD_1		SLASH			QUOTE		LEFT_BRACE	MINUS			UNASSIGNED	UNASSIGNED	
	# 5				v PAD_ENTER top  v mod 00
	muxstrobe 10	UNASSIGNED	UNASSIGNED	INTERNATIONAL_1	BACK_QUOTE	RIGHT_BRACE	EQUAL			UNASSIGNED	UNASSIGNED	
	muxstrobe 11	PAD_3		PAD_6		RSHIFT			ENTER		BACKSLASH	INTERNATIONAL_3	UNASSIGNED	UNASSIGNED	
	# 6
	muxstrobe 12	PAD_2		PAD_5		UNASSIGNED		UNASSIGNED	UNASSIGNED	BACKSPACE		PAD_COMMA	PAD_0	
	muxstrobe 13	PAD_ASTERIX	PAD_9		UNASSIGNED		PAD_4		PAD_8		PAD_SLASH		END			UNASSIGNED	
	# 7										^ orig PAD_1
	muxstrobe 14	PAD_MINUS	PAD_PLUS	UP				DELETE		PAD_7		NUM_LOCK		INSERT		PRINTSCREEN	
	muxstrobe 15	PAD_ENTER	PAD_PERIOD	UNASSIGNED		PAGE_DOWN	PAGE_UP		PAUSE			HOME		SCROLL_LOCK	
	#										^ orig PAD_3
end
