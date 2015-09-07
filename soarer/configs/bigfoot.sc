# IBM 'bigfoot' PC/XT layout 5291-2 terminal keyboard
# Works without modification to keyboard (bigfoot has no CPU!).
# Connnnector: http://www.kbdbabel.org/conn/kbd_connector_ibm5291-2.png
# Wiring: D6:0 -> PB6:0, Strobe -> PD1, Data -> PD0

matrix
	scanrate 1
	debounce 1
	blocking 0
	muxstrobe_port PB6:0
	sense_delay 1
	muxstrobe_gate -PD1
	sense_polarity 1
	
	sense PD0

	muxstrobe  0 Z	# 00
	muxstrobe  1 S	# 01
	muxstrobe  2 W	# 02
	muxstrobe  3 3	# 03
	muxstrobe  4 X	# 04
	muxstrobe  5 D	# 05
	muxstrobe  6 E	# 06
	muxstrobe  7 4	# 07
	muxstrobe  8 C	# 08
	muxstrobe  9 F	# 09
	muxstrobe 10 R	# 0A
	muxstrobe 11 5	# 0B
	muxstrobe 12 V	# 0C
	muxstrobe 13 G	# 0D
	muxstrobe 14 T	# 0E
	muxstrobe 15 6	# 0F
	muxstrobe 16 B	# 10
	muxstrobe 17 H	# 11
	muxstrobe 18 Y	# 12
	muxstrobe 19 7	# 13
	muxstrobe 20 N	# 14
	muxstrobe 21 J	# 15
	muxstrobe 22 U	# 16
	muxstrobe 23 8	# 17
	muxstrobe 24 PAD_2	# 18
	muxstrobe 25 PAD_5	# 19
	muxstrobe 26 PAD_8	# 1A
	muxstrobe 27 NUM_LOCK	# 1B
	muxstrobe 28 M	# 1C
	muxstrobe 29 K	# 1D
	muxstrobe 30 I	# 1E
	muxstrobe 31 9	# 1F
	muxstrobe 32 COMMA	# 20
	muxstrobe 33 L	# 21
	muxstrobe 34 O	# 22
	muxstrobe 35 0	# 23
	muxstrobe 36 PERIOD	# 24
	muxstrobe 37 SEMICOLON	# 25
	muxstrobe 38 P	# 26
	muxstrobe 39 MINUS	# 27
	muxstrobe 40 SLASH	# 28
	muxstrobe 41 QUOTE	# 29
	muxstrobe 42 LEFT_BRACE	# 2A
	muxstrobe 43 EQUAL	# 2B
	muxstrobe 44 SPACE	# 2C
	muxstrobe 45 RSHIFT	# 2D
	muxstrobe 46 BACKSLASH	# 2E              # ~ (ISO)
	muxstrobe 47 RIGHT_BRACE	# 2F
	muxstrobe 48 CAPS_LOCK	# 30
	muxstrobe 49 PAD_ASTERIX	# 31
	muxstrobe 50 ENTER	# 32
	muxstrobe 51 BACKSPACE	# 33
	muxstrobe 52 PAD_0	# 34
	muxstrobe 53 PAD_1	# 35
	muxstrobe 54 PAD_4	# 36
	muxstrobe 55 PAD_7	# 37
	muxstrobe 56 PAD_PLUS	# 38
	muxstrobe 57 UNASSIGNED	# 39
	muxstrobe 58 PAD_MINUS	# 3A
	muxstrobe 59 SCROLL_LOCK	# 3B
	muxstrobe 60 PAD_PERIOD	# 3C
	muxstrobe 61 PAD_3	# 3D
	muxstrobe 62 PAD_6	# 3E
	muxstrobe 63 PAD_9	# 3F
	muxstrobe 64 EUROPE_2	# 40             \ | (ISO)
	muxstrobe 65 A	# 41
	muxstrobe 66 Q	# 42
	muxstrobe 67 2	# 43
	muxstrobe 68 LALT	# 44
	muxstrobe 69 UNASSIGNED	# 45
	muxstrobe 70 UNASSIGNED	# 46
	muxstrobe 71 1	# 47
	muxstrobe 72 F7	# 48
	muxstrobe 73 F5	# 49
	muxstrobe 74 F3	# 4A
	muxstrobe 75 F1	# 4B
	muxstrobe 76 F8	# 4C
	muxstrobe 77 F6	# 4D
	muxstrobe 78 F4	# 4E
	muxstrobe 79 F2	# 4F
	muxstrobe 80 F10	# 50
	muxstrobe 81 UNASSIGNED	# 51
	muxstrobe 82 UNASSIGNED	# 52
	muxstrobe 83 UNASSIGNED	# 53
	muxstrobe 84 F9	# 54
	muxstrobe 85 UNASSIGNED	# 55
	muxstrobe 86 UNASSIGNED	# 56
	muxstrobe 87 UNASSIGNED	# 57
	muxstrobe 88 LSHIFT	# 58
	muxstrobe 89 LCTRL	# 59
	muxstrobe 90 TAB	# 5A
	muxstrobe 91 ESC	# 5B
	muxstrobe 92 UNASSIGNED	# 5C
	muxstrobe 93 UNASSIGNED	# 5D
	muxstrobe 94 UNASSIGNED	# 5E
	muxstrobe 95 UNASSIGNED	# 5F
end	
