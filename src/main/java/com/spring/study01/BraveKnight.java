package com.spring.study01;

import org.springframework.beans.factory.annotation.Qualifier;


public class BraveKnight implements Knight {

  private Quest quest;

  public BraveKnight(Quest quest) {
    this.quest = quest;
  }

  public void embarkOnQuest() {
    quest.embark();
  }

}
