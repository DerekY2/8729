// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TurnToSetpointCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private double setpoint;
  private double turnSetpoint;
  private double error;

  /**
   * Creates a new TurnToSetpointCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnToSetpointCmd(DriveSubsystem driveSubsystem, double setpoint) {
    m_driveSubsystem = driveSubsystem;
    this.setpoint = setpoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("TurnToSetpointCmd initialized");
    turnSetpoint = m_driveSubsystem.getYaw() + setpoint;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      error = turnSetpoint-m_driveSubsystem.getYaw();
      m_driveSubsystem.drive(0.0, -0.007*error);
      m_driveSubsystem.sendToDashboard("turn value", -0.007*error);
      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.drive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (error > 5.0) {
      return false;
    }
    else{  
      return true;
    }
  }
}
