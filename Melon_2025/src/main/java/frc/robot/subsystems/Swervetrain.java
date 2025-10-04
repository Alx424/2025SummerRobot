package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.hardware.SwervetrainHardware;


public class Swervetrain extends SubsystemBase {

    SwervetrainHardware hardware;
    
    public Swervetrain(SwervetrainHardware hardware) {
        this.hardware = hardware;
    }

    public void arcadeDrive(double x, double y, double w) {
        hardware.move(x, y, w);
    }

    public void resetEncoders() {
        hardware.resetEncoders();
    }

    public void resetGyro() {
        hardware.resetGyro();
    }
}
