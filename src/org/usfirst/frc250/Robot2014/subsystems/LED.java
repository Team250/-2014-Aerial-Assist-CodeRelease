/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc250.Robot2014.subsystems;
import org.usfirst.frc250.Robot2014.RobotMap;
import org.usfirst.frc250.Robot2014.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class LED extends Subsystem {
    public void initDefaultCommand() {
        
    }
    private PWM modePWM = new PWM(5);
    private PWM colorPWM = new PWM(6);
    
    public void setMode(int mode) {
        modePWM.setRaw(mode*32-16);
    }
    public void setColor(int color) {
        colorPWM.setRaw(color*32-16);
    }
}
