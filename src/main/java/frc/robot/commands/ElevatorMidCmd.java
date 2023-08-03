// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.ElevatorConstants;

/** An example command that uses an example subsystem. */
public class ElevatorMidCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_elevatorSubsystem;
  Timer timer = new Timer();
  private final double midSpeed = ElevatorConstants.kSpeedMid;

  /**
   * Creates a new ElevatorMidCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ElevatorMidCmd(ElevatorSubsystem subsystem) {
    m_elevatorSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    m_elevatorSubsystem.setMotor(midSpeed/timer.get());

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //SmartDashboard.putNumber("speed of elevator", kp*error/2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.setMotor(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_elevatorSubsystem.getTopLimitSwitch()){
      return true;
    }
    else{
      return false;
    }
  }
}
