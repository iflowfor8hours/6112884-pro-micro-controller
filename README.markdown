-This repo is a fork from iflowfor8hours' repo - consider all the content and excellent work his. I've added a few lines that pertain to MacOSX and added a couple images in docs that helped me.


This project is contains the code necessary to use a pro micro (pictured below) instead of (or) a teensy as the microcontroller for [Soarer's controller](http://deskthority.net/workshop-f7/xt-at-ps2-terminal-to-usb-converter-with-nkro-t2510.html), to replace the controller in an IBM 6112884 Japanese layout keyboard for use today via USB.

## Motivation

The Pro Micro controller is [way cheaper](http://www.alibaba.com/showroom/pro-micro-atmega32u4.html) and easier to get in some cases than the ever diminishing supply of teensy 2.0 controller. 
It is a clone of the Arduino Leonardo with an ATmega32U4 processor.

![pro micro image with teensy pinout](docs/board-pin-diagrams/promicro.png)

The 6112884 was available on ebay super cheap in a big lot a while ago, and also can be bought from [a retailer](http://meci.com/nib-ibm-japanese-lettering-typing-computer-102-keyboard-letter-type-6112884.html) in the US for $24.94. That is incredibly cheap for a full mechanical keyboard with [Alps SKCC switches](http://deskthority.net/wiki/Alps_SKCC_series). This is essentially an IBM Model M for under $30.

The board has a ton of buttons, a nice feel, and an obsolete connector, making it essentially useless without some hacking. 

![japanese layout model m](docs/6112884.jpg)

**Use this board at work. Trust me, it's a good idea.**

## Installation

To get up and running on Mac, you'll need some stuff. I recommend starting by installing Homebrew, which if you don't have it, is rad as hell. As always, `brew update` before you begin.

You'll also need:

- Python 
- Pip
- Python serial (install with pip)
- libusb (brew install libUSB, or brew search it and find the version that works for you)
- AVRDude (Install with Brew)


## Configuration

**Get a good quality USB cable**
I had a couple of micro usb cables laying around that straight up worked for android phones, charging things, other keyboards, etc that would not work for programming my pro micro. Believe it. It happens.

## Running the configuration tools
Run `/dev/tty*` and see what USB devices you've got on the system. Then, connect your Pro Micro and run it again. You should see a new device, and thusly get the system name for the device. Usually it starts with the prefix `UsbModem-`. You can also get device info by going to `finder/about this mac/system hardware/usb`. If you have any issues detecting it, try another USB port. USB 3.0 can cause issues, so try USB 2.0.

Once you have the serial port name, run `./reset.py /dev/ttyACM0` replacing the device name with whatever was correct from the previous command. 
Note that this requires pyserial to work, you can install it using virtualenv and the directions below if you have it, or `sudo pip install pyserial` if you're not into the pragmatisim thing. Might as well do it the right way. To do it with virtualenv run the following:

    virtualenv .venv
    source .venv/bin/activate
    pip install -r requirements.txt

That `reset.py` script should drop your pro micro into bootstrap mode. Now we need to flash it with Soarer's controller software before we can start messing with the layouts and doing useful stuff.
The following command will install the firmware for the pro micro I bought and linked to. Should work on any atmega32u4 (arduino leonardo clone) based device. 

    avrdude -p atmega32u4 -P /dev/ttyACM0  -b 1200 -c avr109  -U flash:w:./soarer/firmware/Soarer_Controller_v1.20_beta4_atmega32u4.hex

You should see some output with various reports of OK. Unplug and replug the device in, and got to `finder/about this mac/system hardware/usb`. You should see a different device name, for example:

    Bus 003 Device 095: ID 16c0:047d Van Ooijen Technische Informatica Teensy Keyboard+Debug

If you do, you're well on your way.

## Assembling and customizing your layout

If you're using the 6112884 Keyboard, then you can just tweak the included config in the `6112884_files/micro_6112884AllKeysAssigned.sc` file. To customize it or make changes to the layout, you need to use Soarer's toolchain to boot, assemble, load and then use it. 

Make whatever changes you like in `6112884_files/micro_6112884AllKeysAssigned.sc` then assemble it into a binary.
  
    ./soarer/tools/scas 6112884_files/micro_6112884AllKeysAssigned.sc 6112884_files/micro_6112884AllKeysAssigned.scb

Run the `scinfo` command to get the state of your device. [This tutorial](http://deskthority.net/workshop-f7/teensy-2-0-alternatives-atmega32u4-t4253-90.html) has some pretty  good troubleshooting info if things don't appear to work.

Now write the compiled binary onto the device using the `scwr` command.

    ./soarer/tools/scwr 6112884_files/micro_6112884AllKeysAssigned.scb

When you unplug and replug the device, your keyboard is ready for debugging the layout for you and typing on hopefully. Note the first line of the `.sc` file corresponds to the pins on your pro micro. They reference the pin names of the teensy though, because that is what Soarer uses. You have to change them to reflect your pro micro which may be different from mine depending on which one you have. The image below has been unbelieveably helpful in figuring out what needs to go where. 

![Teensy to Pro micro pinout](docs/board-pin-diagrams/Teensy2-as-ISP_Pro-Micro.jpg)

Here's Soarer's notation of the original CPU on the 6112884, showing the board names that are associated with the pins. Thanks, Soarer! Appreciate ya.

![pro micro image with teensy pinout](docs/board-pin-diagrams/6112884_cpu_swap_pinout.png)

## Troubleshooting

When I finally got to this phase. I used `xev` extensively to test the keys and ensure the mapping was to my liking. I found this super handy sed command on reddit for filtering the output to get less stuff. Give it a shot if you like. `xev` produces a ton of output otherwise.

    xev | sed -n 's/^.*keycode *\([0-9]\+\).* * \([a-z,A-Z,0-9,_-]\+\)).*$/keycode \1 = \2 /p'

On MacOSX, [https://www.pjrc.com/teensy/hid_listen.html](HID_listen), as recommended by Soarer, is a big help. Make sure to `chmod 755` before you run it, and you may have to `sudo` the executable.

On mine, I had a whole row of keys that didn't work, so I aligned the columns in `6112884_files/micro_6112884AllKeysAssigned.sc` to get a better visual on what was supposed to be happening on each pin of the controller. The line below refers to what cluster of keys are connected to each pin of the pro micro. It was really helpful to line everything up so I could visually see everything while I was testing.

	sense		      PD1			    PD0			    PD4				PC6			    PD7			    PE6				  PB4			    PB5
	# 0
	muxstrobe 0		esc     		TAB			    Z			  	S			      W			      2			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 1		1			      Q			      X			  	D			      E		      	3			    	UNASSIGNED	UNASSIGNED	

There is documentation for all of the soarer tools in the `soarer/docs` directory, however since they don't do much more than what they say they do, I didn't look at them much.

## Contributors

* None of this would have been possible without Soarer and all the contribution he has made, and [this post](https://geekhack.org/index.php?topic=50437.msg1193047#msg1193047) in particular.
* I used a really useful trick to get the pro micro into bootstrap mode that I found in [this repository](https://github.com/nicholaskell/Arduino_Loader). Props the that guy.
* [This article](http://deskthority.net/workshop-f7/how-to-use-a-pro-micro-as-a-cheap-controller-converter-like-soarer-s-t8448.html) about doing basically the same thing was helpful for all the people troubleshooting stuff in it. 
* The reddit [/r/MechanicalKeyboards](https://www.reddit.com/r/MechanicalKeyboards/comments/39pwlo/guidelinux_debug_your_kb_with_command_line_tools/) had that handy sed line for filtering `xev` output.
* As with all keyboard projects, thanks geekhack and deskauthority communities. 

## License

I have no idea. All of the binaries and stuff under the `soarer` directory are from [Soarer's Convertor](http://deskthority.net/workshop-f7/xt-at-ps2-terminal-to-usb-converter-with-nkro-t2510.html), which I don't know how he licenses. The source code for them is included in a zip file in the tools directory.
The configuration files and documentation are my contribution here and fall under MIT license. 
