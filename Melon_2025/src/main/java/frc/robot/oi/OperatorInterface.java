package frc.robot.oi;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {

    Joystick driveController;
    
    public OperatorInterface() {
        driveController = new Joystick(RobotMap.DRIVECON);
    }

    public double getSpeed() {
        return -driveController.getRawAxis(1);
    }

    public double getDirection() {
        return driveController.getRawAxis(4);
    }

    public double getRotate() {
        return driveController.getRawAxis(3); // to be set, unknown axis rn
    }
}
