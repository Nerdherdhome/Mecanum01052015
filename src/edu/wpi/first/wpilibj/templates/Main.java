/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    CANJaguar frontLeft;
    CANJaguar frontRight;
    CANJaguar backRight;
    CANJaguar backLeft;
    Joystick joy;
    Joystick joy2;
    
    public void robotInit() {
        try {
            frontLeft = new CANJaguar(2);
            frontRight = new CANJaguar(3);
            backRight = new CANJaguar(4);
            backLeft = new CANJaguar(5);
            joy = new Joystick(1);
            joy2 = new Joystick(2);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        try{
            if(joy.getRawButton(2)){
                frontLeft.set(1);
            }else if(joy.getRawButton(3)){
                frontRight.set(1);
            }else if(joy.getRawButton(4)){
                backRight.set(1);
            }else if(joy.getRawButton(5)){
                backLeft.set(1);
            }
        }catch(Exception e){
            
        }
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        double forward = joy.getX();
        double strafe = joy.getY();
        double rotate = joy2.getX()/2;
        
        double ftLeft = forward + strafe + rotate;
        double ftRight = -forward + strafe + rotate;
        double bkLeft = forward - strafe + rotate;
        double bkRight = -forward - strafe + rotate;
        
        try{
            frontLeft.set(ftLeft);
            frontRight.set(ftRight);
            backLeft.set(bkLeft);
            backRight.set(bkRight);
        }catch(Exception e){
            System.out.println("Hey Listen");
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
