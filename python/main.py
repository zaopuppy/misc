#!/usr/bin/env python3


from m5stack import *
from m5ui import *
from uiflow import *

import time

LOG_FILE_NAME = "record.log"
g_log_fp = None


def log(msg: str):
  global LOG_FILE_NAME, g_log_fp
  if not g_log_fp:
    g_log_fp = open(LOG_FILE_NAME, "a", encoding="utf-8")
  g_log_fp.write(msg + '\n')
  g_log_fp.flush()


def date_str_sec():
  return ""
  # return time.tick_ms()


setScreenColor(0x222222)


label0 = M5TextBox(0, 113, "PRESS A for beginning, B for ending", lcd.FONT_Default,0xFFFFFF, rotate=0)
title0 = M5Title(title="^_^", x=0, fgcolor=0xFFFFFF, bgcolor=0x0000FF)


def buttonA_wasPressed():
  # global params
  speaker.sing(448, 1/2)
  msg = date_str_sec() + ": begin"
  label0.setText(msg)

btnA.wasPressed(buttonA_wasPressed)

def buttonB_wasPressed():
  # global params
  speaker.sing(494, 1/2)
  msg = date_str_sec() + ": end"
  label0.setText(msg)

btnB.wasPressed(buttonB_wasPressed)

def buttonC_wasPressed():
  # global params
  speaker.sing(262, 1/2)


btnC.wasPressed(buttonC_wasPressed)



