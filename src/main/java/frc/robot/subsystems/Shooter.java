package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.wpilibj2.command.SubsystemBase;

private SparkFlex shooterMotorController;
private SparkClosedLoopController shooterPIDController;
private RelativeEncoder shooterEncoder;
private SparkFlexConfig shooterConfig = new SparkFlexConfig();
private static final double IDLE_SPEED_RPM = 1000;
public class Shooter extends SubsystemBase {

  public Shooter() {
    shooterMotorController = new SparkFlex(2, MotorType, kBrushless);
    shooterPIDController = shooterMotorController.getCLosedLOopController();
    shooterEncoder = shooterMotorController.getEncoder();

    shooterConfig
    .idleMode(IdleMode.kCoast)
    .voltageCOmpensation(12.0)
    .smartCurrentLimit(40);

    shooterConfig.ClosedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      .p(0.5).i(0).d(0)
      .outputRange(-1, 1)
      .positionWrappingENabled(false);

      shooterConfig.closedLoop.feedForward
        .kV(0.2);

      shooterMotorController.configure(shooterConfig, ResetMode.kNoResestSafeParameters, PersistMode.kNoPersistParameters);
  }

  public double getFlyWheelVelocity(){
    return shooterEncoder.getVelocity();
  }

  public void setDesiredFlyWheelSpeed(double speed){
    shooterPIDController.setSetpoint(speed, ControlType.kVelocity, ClosedLoopSlot.kslot0);
    
  public void stopShooter(){
    shooterMotorController.stopMotor();
  }

  public boolean isShooterIdle(){
    double tolerance = 100;
    return Math.abs(getFlyWheelVelocity() - IDLE_SPEED_RPM) <= tolerance;
  }






    
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Not needed for now; hi
  }

}
