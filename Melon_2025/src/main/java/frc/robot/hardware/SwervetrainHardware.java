package frc.robot.hardware;

import frc.robot.RobotMap;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

public class SwervetrainHardware implements ISwervetrainHardware { 

    private SwerveModule l1;
    private SwerveModule r1;
    private SwerveModule l2;
    private SwerveModule r2;
    private AHRS gyro;

    public SwervetrainHardware() {
        l1 = new SwerveModule(RobotMap.DRIVE_L1_FORWARD_CANID, RobotMap.DRIVE_L1_TURNING_CANID, 1, 1);
        l2 = new SwerveModule(RobotMap.DRIVE_L2_FORWARD_CANID, RobotMap.DRIVE_L2_TURNING_CANID, -1, 1);
        r1 = new SwerveModule(RobotMap.DRIVE_R1_FORWARD_CANID, RobotMap.DRIVE_R1_TURNING_CANID, 1, -1);
        r2 = new SwerveModule(RobotMap.DRIVE_R2_FORWARD_CANID, RobotMap.DRIVE_R2_TURNING_CANID, -1, -1);
        gyro = new AHRS(NavXComType.kMXP_SPI);
    }

    public void move(double forward, double turn, double w) {
        // get outputs from each motor
        double[] l1_outputs = l1.getOutputVal(forward, turn, w);
        double[] l2_outputs = l2.getOutputVal(forward, turn, w);
        double[] r1_outputs = r1.getOutputVal(forward, turn, w);
        double[] r2_outputs = r2.getOutputVal(forward, turn, w);

        // if any values are above 1.00, divide all by greatest number
        double max = 0;
        max = Math.max(max, l1_outputs[0]);
        max = Math.max(max, l2_outputs[0]);
        max = Math.max(max, r1_outputs[0]);
        max = Math.max(max, r2_outputs[0]);
        if (max > 1) {
            l1_outputs[0] /= max;
            l2_outputs[0] /= max;
            r1_outputs[0] /= max;
            r2_outputs[0] /= max;
        }
        l1.updateTurningError();
        l1.setMotor(l1_outputs);
        l2.updateTurningError();
        l2.setMotor(l2_outputs);
        r1.updateTurningError();
        r1.setMotor(r1_outputs);
        r2.updateTurningError();
        r2.setMotor(r2_outputs);
    }

    public double getEncoderPos() {
        return 0; // placeholder, to be done
    }

    public void resetEncoders() {
        l1.resetEncoders();
        l2.resetEncoders();
        r1.resetEncoders();
        r2.resetEncoders();
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public void resetGyro() {
        gyro.reset();
        return;
    }
}
