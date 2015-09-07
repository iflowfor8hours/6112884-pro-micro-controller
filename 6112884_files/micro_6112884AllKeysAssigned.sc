# IBM 6112884 Japanese layout with Alps switches
# The CPU was removed from the controller board and replaced with a Teensy, but the 4 to 16 line decoder remains (hence using muxstrobe).
# http://deskthority.net/post114200.html#p114200
# HaaTa's pics of similar keyboard: http://deskthority.net/post87127.html#p87127

#muxstrobe 10 3b changed from back_quote to backspace
#muxstrobe 7 19 changed from App to RGUI
#muxstrobe 0 08 changed from esc to page_down
#muxstrobe 12 5D changed from backspace to back_quote
#muxstrobe 10 2a changed from international_1 (ro, also scancode 073, the Japanese key next to rshift) to rshift so I now have two rshifts
#muxstrobe 2 19 and 5 19 are useless cannot be assigned


matrix
	scanrate 1
	debounce 5
	blocking 0
	muxstrobe_port PF7:4

	#				      0			      1			      2				  3			      4			      5				    6			      7
	#				      8			      9			      A				  B			      C			      D				    E			      F

	sense		      PD1			    PD0			    PD4				PC6			    PD7			    PE6				  PB4			    PB5
	# 0
	muxstrobe 0		esc     		TAB			    Z			  	S			      W			      2			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 1		1			      Q			      X			  	D			      E		      	3			    	UNASSIGNED	UNASSIGNED	
	# 1
	muxstrobe 2		CAPS_LOCK	  unassigned	C			  	F			      R		      	4			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 3		A			      LSHIFT		  V			  	G			      T		      	5			    	UNASSIGNED	UNASSIGNED	
	# 2
	muxstrobe 4		LCTRL		    LGUI		    B			  	H			      Y		      	6			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 5		LALT		    unassigned	N			  	J			      U		      	7			    	UNASSIGNED	UNASSIGNED	
	# 3
	muxstrobe 6		DOWN		    SPACE		    M				  K			      I		      	8			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 7		RALT		    rgui			  COMMA			L			      O		      	9			    	UNASSIGNED	UNASSIGNED	
	# 4
	muxstrobe 8		RCTRL		    LEFT		    PERIOD		SEMICOLON	  P		      	0			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 9		RIGHT		    PAD_1		    slash			QUOTE		    LEFT_BRACE	MINUS			  UNASSIGNED	UNASSIGNED	
	# 5
	muxstrobe 10	f2	f5	    rshift	    backspace	RIGHT_BRACE	EQUAL			  UNASSIGNED	UNASSIGNED	
	muxstrobe 11	PAD_3		    PAD_6		    RSHIFT		ENTER		    BACKSLASH	  esc	        UNASSIGNED	UNASSIGNED	
	# 6
	muxstrobe 12	PAD_2		    PAD_5		    f11		    f10	f9	    back_quote	f3	        PAD_0	
	muxstrobe 13	PAD_ASTERIX	PAD_9		    f7		    PAD_4		    PAD_8		    PAD_SLASH		END			    f8	
	# 7
	muxstrobe 14	PAD_MINUS	  PAD_PLUS	  UP				DELETE		  PAD_7		    NUM_LOCK		INSERT		  PRINTSCREEN	
	muxstrobe 15	PAD_ENTER	  PAD_PERIOD	f12		    PAGE_DOWN	  PAGE_UP		  PAUSE			  HOME		    SCROLL_LOCK	
	#
end


macroblock

macro F7
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS C				
	PRESS T				
	PRESS V				
	POP_META			
	PRESS ENTER			
	POP_ALL_META			
endmacro

macro f8
	PUSH_META CLEAR_META all		
	SET_META ctrl		
	PRESS z				
	POP_META			
	POP_ALL_META
endmacro


macro f9
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS x				
	POP_META			
	POP_ALL_META
endmacro

macro f10
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS c				
	POP_META			
	POP_ALL_META
endmacro

macro f11
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS v				
	POP_META			
	POP_ALL_META
endmacro

macro pad_0
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS w				
	POP_META			
	POP_ALL_META			
endmacro

macro pad_7
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS t				
	POP_META			
	POP_ALL_META
endmacro


macro SLASH ctrl
	PRESS MEDIA_CALCULATOR		
endmacro

macro M ctrl				
	PRESS MEDIA_MUTE		
endmacro

macro insert
	PUSH_META CLEAR_META all		
	SET_META shift			
	PRESS 3
	POP_META			
	POP_ALL_META
endmacro

endblock


macroblock

macro pad_4
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS page_up				
	POP_META			
	POP_ALL_META
endmacro


macro pad_5
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	Set_Meta shift
	press t				
	POP_META			
	POP_ALL_META
endmacro

macro pad_6
	PUSH_META CLEAR_META all		
	SET_META ctrl			
	PRESS page_down				
	POP_META			
	POP_ALL_META
endmacro


endblock
