import random
import sys
import pygame

def create_cup():
    pos = random.choice(cup_height)
    mid_cup = cup.get_rect(center = (1500, pos))
    return mid_cup
    

def move_cup(cups):
    for c in cups:
        c.centerx -= 3
    return cups
 
def draw_cup(cups):
    for c in cups:
        screen.blit(cup, c)

    # tao cup
cup = pygame.image.load('assets/cup.png').convert_alpha()
ufo = pygame.image.load('assets/ufo.png').convert_alpha()
cup_list = []