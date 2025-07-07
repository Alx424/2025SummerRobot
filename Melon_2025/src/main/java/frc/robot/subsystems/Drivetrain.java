// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.hardware.DrivetrainHardware;

public class Drivetrain extends SubsystemBase {
  DrivetrainHardware hardware;

  public Drivetrain(DrivetrainHardware hardware) {
    this.hardware = hardware;
  }

  public void arcadeDrive(double speed, double rotate) {
    hardware.setLeftSide(speed + rotate);
    hardware.setRightSide(speed - rotate);
  }

  public void resetGyro() {
    hardware.resetGyro();
  }

  public void resetEncoders() {
    hardware.resetEncoders();
  }

}
