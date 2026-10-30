package frc.robot.subsystems;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private TalonFX elevatorMotorController1;
    private TalonFX elevatorMotorController2;
    private TalonFXConfig elevatorConfig = new TalonFXConfig();
    private double setpointHeight = 0.0;
    boolean positionControlEnabled = false;


   
    

public Elevator(){
    elevatorMotorController1 = new TalonFX(10);
    elevatorMotorController2 = new TalonFX(11);
    

   




    leaderConfig.encoder
        .positionConversionFactor(2.0 * Math.PI * Units.inchesToMeters(1.75) / 5.0);
        .velocityConversionFactor(2.0 * Math.PI * Units.inchesToMeters(1.75) / 5.0 / 60.0);




    leaderConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        p(0.83).i(0.0).d(0.0);
        .feedForward
        kS(0.584)
        kG(0.366)
        kV(2.15)
        kA(0.05)
        .outputRange(-1, 1)

    .maxMotion
        .cruiseVelocity(0.75)
        .maxAcceleration(1.0)
        .allowedProfileError(0.01)

    followerConfig
        idleMode(IdleMode.kBrake)
        .smartCurrentLimit(40)
        .voltageCompensation(12)
        .follow(elevatorMotor1, true)

    elevatorMotorController.configure(elevatorConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

}

getElevatorHeight(){
    return relativeEncoder.getPosition;
}

getElevatorVelocity(){
    return relativeEncoder.getVelocity;

}

setDesiredHeight(double heightMeters){
    setPointHeight = MathUtil.clamp(heightMeters, 0.00, 1.44);

    elevatorController.setPoint(height, ControlType.kMAXPositionControl, ClosedLoopSlot.kSlot0);
    positionControlEnabled = true;


}


setElevatorSpeed(double speed){
    positionControlEnabled = false;
    setSpeed = MathUtil.clamp(speed, -1.0, 1.0);
    elevatorMotor1.set(setSpeed);


}

setElevatorVoltage(double voltage){
    positionControlEnabled = false;
    setVoltage = MathUtil.clamp(voltage, -12,0, 12.0);
    elevatorMotor1.set(setVoltage);

}

public void stop(){
    elevatorMotor1.stopMotor();

}

public void zeroEncoder(){
    stop();
    relativeEncoder.setPosition(0.0);
    setpointHeight();

public boolean isAtSetpoint(){
    return positionControlEnabled && (Math.abs(getPosition() - setpointHeight) < Units.inchesToMeters(1));
}


}




  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Not needed for now
  }

}

