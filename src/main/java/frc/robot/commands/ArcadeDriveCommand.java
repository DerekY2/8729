// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Drive command

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class ArcadeDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final DoubleSupplier speedFunction, turnFunction;

  double xSpeed;
  double angular;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArcadeDriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier speedFunction, DoubleSupplier turnFunction) {
    this.m_driveSubsystem = driveSubsystem;
    this.speedFunction = speedFunction;
    this.turnFunction = turnFunction;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(!DriveConstants.setMaximumDriveSpeeds){
      m_driveSubsystem.setMotor(speedFunction.getAsDouble(), turnFunction.getAsDouble());
    }
    //System.out.println(speedFunction.getAsDouble() + ", " + turnFunction.getAsDouble());
    else{
      checkMaxSpeeds(speedFunction, turnFunction);
    }

    // just for debugging
    if(speedFunction.getAsDouble() < 0.0 || speedFunction.getAsDouble() > 0.0){
      m_driveSubsystem.logDrive("Driving");
    }
    else if(speedFunction.getAsDouble() == 0.0){
      m_driveSubsystem.logDrive("Stopped");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.setMotor(0.0, 0.0);
    m_driveSubsystem.logDrive("Driving Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  // check if speeds exceed preset max speeds, if so, set motors to the preset max speeds
  public void checkMaxSpeeds(DoubleSupplier checkSpeed, DoubleSupplier checkTurn){
    if(checkSpeed.getAsDouble() >= DriveConstants.maxDriveSpeed){
      xSpeed = DriveConstants.maxDriveSpeed;
    }
    else if(checkSpeed.getAsDouble() <= -DriveConstants.maxDriveSpeed){
      xSpeed = -DriveConstants.maxDriveSpeed;
    }
    else{
      xSpeed = checkSpeed.getAsDouble();
    }

    if(checkTurn.getAsDouble() >= DriveConstants.maxTurnSpeed){
      angular = DriveConstants.maxTurnSpeed;
    }
    else if(checkTurn.getAsDouble() <= -DriveConstants.maxTurnSpeed){
      angular = -DriveConstants.maxTurnSpeed;
    }
    else{
      angular = checkTurn.getAsDouble();
    }

    m_driveSubsystem.setMotor(speedFunction.getAsDouble(), turnFunction.getAsDouble());
  }
}
