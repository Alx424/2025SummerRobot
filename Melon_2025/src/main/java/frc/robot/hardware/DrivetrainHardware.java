package frc.robot.hardware;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder; 

public class DrivetrainHardware implements IDrivetrainHardware {

    private SparkLowLevel l1;
    private SparkLowLevel l2;
    private SparkLowLevel r1;
    private SparkLowLevel r2;
    private AHRS gyro;
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    public DrivetrainHardware() {
        l1 = new SparkMax(RobotMap.DRIVE_L1_CANID, MotorType.kBrushless);
        l2 = new SparkMax(RobotMap.DRIVE_L2_CANID, MotorType.kBrushless);
        r1 = new SparkMax(RobotMap.DRIVE_R1_CANID, MotorType.kBrushless);
        r2 = new SparkMax(RobotMap.DRIVE_R2_CANID, MotorType.kBrushless);
        gyro = new AHRS(NavXComType.kMXP_SPI);
        leftEncoder = new Encoder(RobotMap.DIO_PORT_A_LEFT_ENCODER, RobotMap.DIO_PORT_B_LEFT_ENCODER);
        rightEncoder = new Encoder(RobotMap.DIO_PORT_A_RIGHT_ENCODER, RobotMap.DIO_PORT_B_RIGHT_ENCODER);
    }

    public void setLeftSide(double val) {
        l1.set(capInput(val, -1, 1));
        l2.set(capInput(val, -1, 1));
        return;
    }

    public void setRightSide(double val) {
        r1.set(capInput(val, -1, 1));
        r2.set(capInput(val, -1, 1));
        return;
    }

    public double getEncoderPos() {
        return (leftEncoder.getDistance()+rightEncoder.getDistance())/2;
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
        return;
    }

    public double getGyroAngle() {
        return gyro.getYaw();
    }

    public void resetGyro() {
        gyro.reset();
        return;
    }

    public double capInput(double val, double min, double max) {
        if(val < min) return min;
        if(val > max) return max;
        return val;
    }
}
