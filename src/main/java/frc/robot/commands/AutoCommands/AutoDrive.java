// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final double speed;
  private final double rotation;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoDrive(DriveSubsystem subsystem, double xSpeed, double rotation) {
    m_driveSubsystem = subsystem;
    this.speed = xSpeed;
    this.rotation = rotation;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AutoDrive Started");
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveSubsystem.drive(speed, rotation);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.drive(AutoConstants.kInterrupted, AutoConstants.kInterrupted);
    System.out.println("AutoDrive Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}



/*
 *  // ends command when the current encoder distance is smaller than the specified distance
 * if(m_driveSubsystem.getEncoderDistance() > encoderDistance){
     return false;
    }
    else{
      return true;
    }
 */