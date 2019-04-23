package com.spring.Spring_07;

import org.springframework.transaction.annotation.Transactional;

public class DamselRescuingKnight implements Knight {

  private RescueDamselQuest quest;

  public DamselRescuingKnight() {
    this.quest = new RescueDamselQuest();
  }

  @Transactional
  public void embarkOnQuest() {
    quest.embark();
  }

}
