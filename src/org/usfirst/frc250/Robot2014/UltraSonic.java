/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc250.Robot2014;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;

/**
 *
 * @author Programmers
 */
public class UltraSonic extends SensorBase {
    
    private I2C device;
    private boolean writeError = false;
    private boolean readError = false;
    private final byte[] rangeCommand = new byte[1];
    private final byte[] rangeRead = new byte[2];

    public UltraSonic(int deviceAddress) {
        this(1,deviceAddress);
    }
    //0x1B
    public UltraSonic(int digitalModule, int deviceAddress) {
        DigitalModule module = DigitalModule.getInstance(digitalModule);
        device = module.getI2C(deviceAddress);
        rangeCommand[0] = (byte)81;

        System.out.println("I2C Verify: " + !device.addressOnly());
    }

    public void startRanging(){
        System.out.println("Start Ranging");
        writeError = device.transaction(rangeCommand, 1, null, 0);
        if (writeError) {
            System.out.println("Write Failed!");
        }
    }
    public int getDistance() {
        
        System.out.println("Get Distance");
        readError = device.transaction(null, 0, rangeRead, 2);
        if (readError) {
            System.out.println("Read failed");
        } 
        return (rangeRead[0]<<8) | (rangeRead[1] & 0xFF);
    }

}
