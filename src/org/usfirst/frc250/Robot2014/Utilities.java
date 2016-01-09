/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc250.Robot2014;

import edu.wpi.first.wpilibj.Ultrasonic;
import org.usfirst.frc250.Robot2014.RobotMap;

/**
 *
 * @author Programmers
 */
public class Utilities {

    public static double deadband(double input, double deadbandWidth, double minInput, double maxInput, double minOutput, double maxOutput) {
        double centerInput, centerOutput, leftYInt, rightYInt, lineSlope;

        centerInput = (maxInput + minInput) / 2;
        centerOutput = (maxOutput + minOutput) / 2;
        lineSlope = (maxOutput - centerOutput) / (maxInput - centerInput - 2 * deadbandWidth);

        if (input < minInput || input > maxInput) {
            //Invalid input, returning least dangerous value
            return centerOutput;
        }

        if (input < minInput + deadbandWidth) {
            //Lower deadband
            return minOutput;
        } else if (input < centerInput - deadbandWidth) {
            //Slope of left line
            leftYInt = minOutput - lineSlope * (minInput + deadbandWidth);
            return (lineSlope * input + leftYInt);
        } else if (input < centerInput + deadbandWidth) {
            //Center deadband
            return centerOutput;
        } else if (input < maxInput - deadbandWidth) {
            //Slope of right line
            rightYInt = maxOutput - lineSlope * (maxInput - deadbandWidth);
            return (lineSlope * input + rightYInt);
        } else {
            //Upper deadband
            return maxOutput;
        }
    }

    public static double joystickDeadband(double input) {
//        return deadband(input, 0.11, -1.0, 1.0, -1.0, 1.0);
        return deadband(input, RobotMap.kDeadbandWidth, -1.0, 1.0, -1.0, 1.0);
    }
//
//    public static double analogInputDeadband(double input) {
//        return deadband(input, 0.06, 0, 5, -1, 1);
//    }

    public static double MotorRamp(double maxChange, double target, double current) {
        //If the target is within the range of maxChange
        if (maxChange + current > target && current - maxChange < target) {
            //System.out.println("@Target" + " T:" + target + " C:" + current + " Output:" + target);
            return target;
        } //If the current is less than the target and out of range of the target
        else if (current < target) {
            //System.out.println("+Target" + " T:" + target + " C:" + current + " Output:" + (current+maxChange));
            return current + maxChange;
        } //If the current is greater than the target and out of range of the target
        else if (current > target) {
            //System.out.println("-Target" + " T:" + target + " C:" + current + " Output:" + (current-maxChange));
            return current - maxChange;
        } //THIS IS A BAD THING. WE SHOULD NOT GET HERE. IF WE DO, YELL, "KYLE!!!!!!!!"
        else {
            //System.out.println("Zero" + " T:" + target + " C:" + current + " Output:" + 0);
            return 0;
        }
    }
}
