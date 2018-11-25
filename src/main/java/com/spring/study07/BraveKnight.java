package com.spring.study07;

import org.springframework.transaction.annotation.Transactional;

public class BraveKnight implements Knight {

  private Quest quest;

  public BraveKnight(Quest quest) {
    this.quest = quest;
  }

  @Transactional
  public void embarkOnQuest() {
    quest.embark();
  }

}
