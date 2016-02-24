This project is contains the code necessary to use a pro micro (pictured below) instead of (or) a teensy as the microcontroller for [Soarer's controller](http://deskthority.net/workshop-f7/xt-at-ps2-terminal-to-usb-converter-with-nkro-t2510.html), to replace the controller in an IBM 6112884 Japanese layout keyboard for use today via USB.

## Motivation

The Pro Micro controller is [way cheaper](http://www.alibaba.com/showroom/pro-micro-atmega32u4.html) and easier to get in some cases than the ever diminishing supply of teensy 2.0 controller. 
It is a clone of the Arduino Leonardo with an ATmega32U4 processor.

![pro micro image with teensy pinout](docs/promicro.png)

The 6112884 was available on ebay super cheap in a big lot a while ago, and also can be bought from [a retailer](http://meci.com/nib-ibm-japanese-lettering-typing-computer-102-keyboard-letter-type-6112884.html) in the US for $24.94. That is incredibly cheap for a full mechanical keyboard with [Alps SKCC switches](http://deskthority.net/wiki/Alps_SKCC_series). This is essentially an IBM Model M for under $30.

The board has a ton of buttons, a nice feel, and an obsolete connector, making it essentially useless without some hacking. 

![japanese layout model m](docs/6112884.jpg)

**Don't use this board at work, it is LOUD, and your coworkers will kill you, or if you work in London, like I do, make them want to kill you but not tell you that..**

## Installation

To get up and running on linux, you'll need a few dependencies. Namely the avr-gcc toolchain, and some other stuff. 

`sudo apt-get install flex byacc bison gcc libusb-1.0-0-dev libusb-dev libc6-dev avrdude libusb-0.1-4:i386`

There are probably a bunch of usb-related libraries that I missed there as well. Things will balk when they need them. I have been programming different microcontrollers on the same machine for a while so I don't know what else is really required on a clean machine at this point. The killer one is that i386 libusb library, it's needed to put the pro micro into bootstrap mode.

## Installation on MacOSX

To get up and running on Mac, you'll need some stuff. I recommend starting by installing Homebrew, which if you don't have it, is the rad-as-hell apt-get type analog for Mac. As always, `brew update` before you begin. I'd also recommend installing [Crosspack for AVR](https://www.obdev.at/products/crosspack/index.html), which is a development environment for AVR microcontrollers, and at the very least it will make sure you've got the right USB drivers.

You'll also need:

- Python (install from the public site with a PKG)
- Pip 
- Python serial (install with pip)
- libusb (brew install libUSB, or brew search it and find the version that works for you)
- AVRDude (Install with Brew)

## Configuration

**Get a good quality USB cable**
I had a couple of micro usb cables laying around that straight up worked for android phones, charging things, other keyboards, etc that would not work for programming my pro micro. Believe it. It happens.

## Running the configuration tools

Plug your pro micro in, then run `lsusb`. You might need to sudo that. if it is attached, you should see something like

    Bus 003 Device 091: ID 2341:8036 Arduino SA Leonardo (CDC ACM, HID)

In your output. That means we can see it. I've read elsewhere that you might need to add a udev rule to prevent it from getting picked up by ubuntu as a device. I didn't but here's how to do that just in case. 

    sudo echo 'ATTRS{idVendor}=="1b4f", ENV{ID_MM_DEVICE_IGNORE}="1"' > /etc/udev/rules.d/77-arduino.rules

We need to load soarer's controller onto the device to get anything useful to happen concerning keyboards. To do that, the pro micro needs to be in bootstrap mode. The included `reset.py` script will do that for you, provided you know what serial port it is on. To find out, unplug your device and run

    sudo ls /dev/tty*

Then plug the device in, and run it again. Find the difference. Mine was set to `/dev/ttyACM0` but that may be different for you depending on what other devices you have plugged in.

Once you have the serial port name, run `./reset.py /dev/ttyACM0` replacing the device name with whatever was correct from the previous command. 
Note that this requires pyserial to work, you can install it using virtualenv and the directions below if you have it, or `sudo pip install pyserial` if you're not into the pragmatisim thing. Might as well do it the right way. To do it with virtualenv run the following:

    virtualenv .venv
    source .venv/bin/activate
    pip install -r requirements.txt

That `reset.py` script should drop your pro micro into bootstrap mode. Now we need to flash it with Soarer's controller software before we can start messing with the layouts and doing useful stuff.
The following command will install the firmware for the pro micro I bought and linked to. Should work on any atmega32u4 (arduino leonardo clone) based device. 

    avrdude -p atmega32u4 -P /dev/ttyACM0  -c avr109  -U flash:w:./soarer/firmware/Soarer_Controller_v1.20_beta4_atmega32u4.hex

You should see some output with various reports of OK. Unplug and replug the device in, and run `lsusb` again. Now you should see the following output, with likely different bus and device IDs.

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

![Teensy to Pro micro pinout](docs/Teensy2-as-ISP_Pro-Micro.jpg)

## Important notes about corresponding pins

Here's Soarer's notation of the original CPU on the 6112884, showing the board names that are associated with the pins. Thanks, Soarer! Appreciate ya.

![pro micro image with teensy pinout](docs/6112884_cpu_swap_pinout.png)

Make sure you double-check the pins you've soldered to on the Pro Micro! If you're using iflowfor8hours' layout config for the 6112884 (which is in
6112884_files/micro_6112884AllKeysAssigned.sc), you'll see he's labelled Sense 0-7. So has Soarer on the chart above, but the pins correspond to the pins on a teensy, and because I'm an idiot I found that confusing. I've created a list of the corresponding pins from the chip on the IBM board below, and you can cross-reference it with Soarer's image of the chip above with the green labels. Ground and VCC are clearly labelled on the chips, and remember that the u-shaped cut-out in the chip faces left if you're looking at the keyboard from the front on.

IBM CHIP PIN / PRO MICRO INPUT

* Sense 0 / PD1
* Sense 1 / PD0
* Sense 2 / PD4
* Sense 3 / PC6
* Sense 4 / PD7
* Sense 5 / PE6
* Sense 6 / PB4
* Sense 7 / PB5

* Strobe Mux 3 / A0
* Strobe Mux 2 / A1
* Strobe Mux 1 / A2
* Strobe Mux 0 / A3

## Troubleshooting

When I finally got to this phase. I used `xev` extensively to test the keys and ensure the mapping was to my liking. I found this super handy sed command on reddit for filtering the output to get less stuff. Give it a shot if you like. `xev` produces a ton of output otherwise.

    xev | sed -n 's/^.*keycode *\([0-9]\+\).* * \([a-z,A-Z,0-9,_-]\+\)).*$/keycode \1 = \2 /p'

On mine, I had a whole row of keys that didn't work, so I aligned the columns in `6112884_files/micro_6112884AllKeysAssigned.sc` to get a better visual on what was supposed to be happening on each pin of the controller. The line below refers to what cluster of keys are connected to each pin of the pro micro. It was really helpful to line everything up so I could visually see everything while I was testing.

	sense		      PD1			    PD0			    PD4				PC6			    PD7			    PE6				  PB4			    PB5
	# 0
	muxstrobe 0		esc     		TAB			    Z			  	S			      W			      2			    	UNASSIGNED	UNASSIGNED	
	muxstrobe 1		1			      Q			      X			  	D			      E		      	3			    	UNASSIGNED	UNASSIGNED	

There is documentation for all of the soarer tools in the `soarer/docs` directory, however since they don't do much more than what they say they do, I didn't look at them much.

##MacOSX Troubleshooting

If you recompile the .SC file using Soarer's tools SCAS, make sure to use the 1.2 beta version in the zip in this repo, (and obviously the Mac one). For me, the 1.1 tools didn't work great on Mac El Capitan.

On MacOSX (I haven't used XEV on Mac, but you could probably get it), [HID_listen](https://www.pjrc.com/teensy/hid_listen.html), as recommended by Soarer on the forums, is a big help. Make sure to `chmod 755` before you run it, and you may have to `sudo` the executable. I've included it in /HID/, and you can read more about it in /soarer/docs. It'll tell you most everything you need to know about what keypresses are happening and what data is being recieved, or if keypresses are being detected at all.

## Contributors

* None of this would have been possible without Soarer and all the contribution he has made, and [this post](https://geekhack.org/index.php?topic=50437.msg1193047#msg1193047) in particular.
* I used a really useful trick to get the pro micro into bootstrap mode that I found in [this repository](https://github.com/nicholaskell/Arduino_Loader). Props the that guy.
* [This article](http://deskthority.net/workshop-f7/how-to-use-a-pro-micro-as-a-cheap-controller-converter-like-soarer-s-t8448.html) about doing basically the same thing was helpful for all the people troubleshooting stuff in it. 
* The reddit [/r/MechanicalKeyboards](https://www.reddit.com/r/MechanicalKeyboards/comments/39pwlo/guidelinux_debug_your_kb_with_command_line_tools/) had that handy sed line for filtering `xev` output.
* As with all keyboard projects, thanks geekhack and deskauthority communities. 

## License

I have no idea. All of the binaries and stuff under the `soarer` directory are from [Soarer's Convertor](http://deskthority.net/workshop-f7/xt-at-ps2-terminal-to-usb-converter-with-nkro-t2510.html), which I don't know how he licenses. The source code for them is included in a zip file in the tools directory.
The configuration files and documentation are my contribution here and fall under MIT license. 
