package com.diozero.internal.provider.mmap;

/*
 * #%L
 * Device I/O Zero - Java Native provider for the Raspberry Pi
 * %%
 * Copyright (C) 2016 mattjlewis
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */


import org.pmw.tinylog.Logger;

import com.diozero.api.DeviceMode;
import com.diozero.internal.provider.AbstractDevice;
import com.diozero.internal.provider.GpioDigitalOutputDeviceInterface;
import com.diozero.util.RuntimeIOException;

public class MmapDigitalOutputDevice extends AbstractDevice implements GpioDigitalOutputDeviceInterface {
	private MmapDeviceFactory mmapDeviceFactory;
	private int gpio;

	MmapDigitalOutputDevice(MmapDeviceFactory deviceFactory, String key,
			int gpio, boolean initialValue) {
		super(key, deviceFactory);
		
		this.mmapDeviceFactory = deviceFactory;
		this.gpio = gpio;
		
		deviceFactory.getMmapGpio().setMode(gpio, DeviceMode.DIGITAL_OUTPUT);
		deviceFactory.getMmapGpio().gpioWrite(gpio, initialValue);
	}

	@Override
	public int getGpio() {
		return gpio;
	}

	@Override
	public boolean getValue() throws RuntimeIOException {
		return mmapDeviceFactory.getMmapGpio().gpioRead(gpio);
	}

	@Override
	public void setValue(boolean value) throws RuntimeIOException {
		mmapDeviceFactory.getMmapGpio().gpioWrite(gpio, value);
	}

	@Override
	public void closeDevice() {
		Logger.debug("closeDevice()");
		// FIXME No GPIO close method?
		// TODO Revert to default input mode?
		// What do wiringPi / pigpio do?
	}
}