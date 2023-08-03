// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.Constants.AutoConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/** An example command that uses an example subsystem. */
public class AutoIntakeRotation extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeChanPart1 m_intakeChanPart1;
  private final double time;
  private final double speed;
  Timer timer = new Timer();
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoIntakeRotation(IntakeChanPart1 subsystem, double timeout, double speed) {
    this.m_intakeChanPart1 = subsystem;
    this.time = timeout;
    this.speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AutoIntakeRotation Started");
     timer.reset();
     timer.start();
     
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeChanPart1.setMotor(speed);
    System.out.println(timer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeChanPart1.setMotor(AutoConstants.kInterrupted);
    System.out.println("AutoIntakeRotation Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() < time){ // end command after reaching timeout
      return false;
    }
    else{
      return true;
    }
  }
}
