// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/** An example command that uses an example subsystem. */
public class AutoDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final boolean timed;
  private final boolean encoders;
  private final double time;
  private final double encoderDistance;
  private final double speed;
  private final double rotation;
  private final boolean inverted;
  Timer timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoDrive(DriveSubsystem subsystem, boolean timed, boolean encoders, double timeout, double encoderDistance, double xSpeed, double rotation) {
    m_driveSubsystem = subsystem;
    this.timed = timed;
    this.encoders = encoders;
    this.time = timeout;
    this.encoderDistance = encoderDistance;
    this.speed = xSpeed;
    this.rotation = rotation;
    this.inverted = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

  }

  // same as the above constructor, but encoder ending condition is inverted.
  public AutoDrive(DriveSubsystem subsystem, boolean timed, boolean encoders, boolean invertFinishCondition, double timeout, double encoderDistance, double xSpeed, double rotation) {
    m_driveSubsystem = subsystem;
    this.timed = timed;
    this.encoders = encoders;
    this.time = timeout;
    this.encoderDistance = encoderDistance;
    this.speed = xSpeed;
    this.rotation = rotation;
    this.inverted = invertFinishCondition;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AutoDrive Started");
    if(timed){
      timer.reset();
      timer.start();
    }
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveSubsystem.drive(speed, rotation);
    System.out.println(timer.get());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.drive(AutoConstants.kInterrupted, AutoConstants.kInterrupted);
    if(encoders){
      m_driveSubsystem.resetEncoder();
    }
    System.out.println("AutoDrive Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timed){
      if(timer.get() < time){ // ends command when timeout reached
        return false;
      }
      else{
        return true;
      }
    }
    if(encoders){
        if(inverted){ // ends command when the current encoder distance is smaller than the specified distance
          if(m_driveSubsystem.getEncoderDistance() > encoderDistance){
            return false;
          }
          else{
            return true;
          }
        }
        else{ // ends command when the current encoder distance is greater than the specified distance
          if(m_driveSubsystem.getEncoderDistance() < encoderDistance){
            return false;
          }
          else{
            return true;
          }
        }
    }
    else{
      return false;
    }
  }
}
