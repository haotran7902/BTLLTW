import random
import sys
import pygame

    # tao ham cho tro choi
def draw_floor(): 
    screen.blit(floor, (floor_x_pos, 650))
    screen.blit(floor, (floor_x_pos + 432, 650))

def create_pipe():
    random_pipe_pos = random.choice(pipe_height)
    bottom_pipe = pipe_surface.get_rect(midtop = (436, random_pipe_pos))
    bottom_pipe2 = pipe_surface.get_rect(midtop = (648, random_pipe_pos))
    top_pipe = pipe_surface.get_rect(midtop = (436, random_pipe_pos - 780))
    top_pipe2 = pipe_surface.get_rect(midtop = (648, random_pipe_pos - 780))
    return bottom_pipe, top_pipe, bottom_pipe2, top_pipe2

def create_pipe1():
    random_pipe_pos = random.choice(pipe_height)
    bottom_pipe = pipe_surface.get_rect(midtop = (436, random_pipe_pos))
    bottom_pipe1 = pipe_surface.get_rect(midtop = (540, random_pipe_pos))
    bottom_pipe2 = pipe_surface.get_rect(midtop = (649, random_pipe_pos))
    bottom_pipe3 = pipe_surface.get_rect(midtop = (756, random_pipe_pos))
    top_pipe = pipe_surface.get_rect(midtop = (436, random_pipe_pos - 780))
    top_pipe1 = pipe_surface.get_rect(midtop = (540, random_pipe_pos - 780))
    top_pipe2 = pipe_surface.get_rect(midtop = (649, random_pipe_pos - 780))
    top_pipe3 = pipe_surface.get_rect(midtop = (756, random_pipe_pos - 780))
    return bottom_pipe, top_pipe, bottom_pipe1, top_pipe1, bottom_pipe2, top_pipe2, bottom_pipe3, top_pipe3

def move_pipe(pipes):
    for pipe in pipes:
        pipe.centerx -= speedX
    return pipes

def draw_pipe(pipes):
    for pipe in pipes:
        if pipe.bottom >= 600: 
            screen.blit(pipe_surface, pipe)
        else:
            flip_pipe = pygame.transform.flip(pipe_surface, False, True)
            # False True: lat theo truc y, ko lat theo truc x
            screen.blit(flip_pipe, pipe)

def check_collision(pipes):
    for pipe in pipes:
        if bird_rect.colliderect(pipe):
            hit_sound.play()
            return False
    if bird_rect.top <= -75 or bird_rect.bottom >= 650:
        return False
    return True

def create_cup():
    pos = random.choice(cup_height)
    mid_cup = cup.get_rect(center = (750, pos))
    return mid_cup
    
def move_cup(cups):
    for c in cups:
        c.centerx -= speedX
    return cups

def draw_cup(cups):
    for c in cups:
        screen.blit(cup, c)

def check_collision_cup(cups):
    for c in cups:
        if bird_rect.colliderect(c):
            cups.remove(c)
            return False
    return True

def create_ufo():
    pos = random.choice(ufo_height)
    mid_ufo = cup.get_rect(center = (1000, pos))
    return mid_ufo
    
def move_ufo(ufos):
    for c in ufos:
        c.centerx -= speedX
    return ufos
 
def draw_ufo(ufos):
    for c in ufos:
        screen.blit(ufo, c)

def check_collision_ufo(ufos):
    for c in ufos:
        if bird_rect.colliderect(c):
            ufos.remove(c)
            return False
    return True

def create_bullet():
    pos = random.choice(bullet_height)
    mid_ufo = cup.get_rect(center = (1200, pos))
    return mid_ufo
    
def move_bullet(ufos):
    for c in ufos:
        c.centerx -= 6
    return ufos
 
def draw_bullet(ufos):
    for c in ufos:
        screen.blit(bullet, c)

def check_collision_bullet(ufos):
    for c in ufos:
        if bird_rect.colliderect(c):
            ufos.remove(c)
            return False
    return True

def rotate_bird(bird1):
    new_bird = pygame.transform.rotozoom(bird1, -bird_movement*3, 1)
    return new_bird
  
def bird_animation():
    new_bird = bird_list[0]
    new_bird_rect = new_bird.get_rect(center = (100, bird_rect.centery))
    return new_bird, new_bird_rect

def score_display(game_state):
    if game_state == 'main game':
        score_surface = game_font.render(str(int(score)),True,(255,255,255))
        score_rect = score_surface.get_rect(center = (100,100))
        screen.blit(score_surface,score_rect)

        score_surface1 = game_font.render(str(int(red_card)),True,(240, 54, 7))
        score_rect1 = score_surface1.get_rect(center = (300,100))
        screen.blit(score_surface1,score_rect1)
    if game_state == 'game_over':
        score_surface = game_font.render(f'Goals: {int(score)}',True,(255,255,255))
        score_rect = score_surface.get_rect(center = (216,100))
        screen.blit(score_surface,score_rect)

        high_score_surface = game_font.render(f'High Goals: {int(high_score)}',True,(255,255,255))
        high_score_rect = high_score_surface.get_rect(center = (216,630))
        screen.blit(high_score_surface,high_score_rect)

def update_score(score, high_score):
    if score > high_score:
        high_score = score
    return high_score

pygame.mixer.pre_init(frequency=44100, size=-16, channels=2, buffer=512) # convert de pygame de doc hon
pygame.init() 
screen = pygame.display.set_mode((432,768))
clock = pygame.time.Clock()
game_font = pygame.font.Font('04B_19.ttf', 40)

    # tao cac bien cho tro choi
gravity = 0.2  # trong luc
bird_movement = 0   # luc dau con chim chua di chuyen
game_active = True
score = 0
red_card = 0
count = 0
high_score = 0
collision = True
collision1 = True
collision2 = True
speedX = 3

    # chen background
bg = pygame.image.load('assets/worldcup.png').convert() # dung convert de load nhanh hon

    # tao san 
floor = pygame.image.load('assets/grass.png').convert_alpha() # san ben duoi
floor = pygame.transform.scale2x(floor)
floor1 = pygame.image.load('assets/grass.png').convert_alpha() # san ben duoi
floor1 = pygame.transform.scale2x(floor)
floor_x_pos = 0

    # tao ra con chim  
bird_mid = pygame.image.load('assets/maguire.png')
bird_mid = pygame.transform.scale(bird_mid, (60, 60))
bird_list = [bird_mid]
bird = bird_list[0] 
bird_rect = bird.get_rect(center = (100, 384))   
maguire = pygame.image.load('assets/maguire.png')
messi = pygame.image.load('assets/messi.png')
ronaldo = pygame.image.load('assets/ronaldo.png')

    # tao timer cho bird
bird_flap = pygame.USEREVENT + 1
pygame.time.set_timer(bird_flap, 200)

    # tao cup
cup = pygame.image.load('assets/ball.png').convert_alpha()
ufo = pygame.image.load('assets/gold-ball.png').convert_alpha()
cup_list = []
ufo_list = []
    #tao dan
bullet = pygame.image.load('assets/red-card.png').convert_alpha()
bullet_list = []

spawncup = pygame.USEREVENT
pygame.time.set_timer(spawncup, 1200) # sau 1.2s thi tao ra ong moi
cup_height = [250, 280, 300, 320]
ufo_height = [250, 280, 300, 320]
bullet_height = [200, 230, 250, 280, 300, 320, 340, 370]
 
    # tao ong 
pipe_surface = pygame.image.load('assets/pipe-gold.png').convert_alpha()
pipe_surface = pygame.transform.scale2x(pipe_surface)
pipe_list = [] # list luu cac ong vua moi tao ra

    # tao timer cho ong exist lien tuc
spawnpipe = pygame.USEREVENT
pygame.time.set_timer(spawnpipe, 1200) # sau 1.2s thi tao ra ong moi
pipe_height = [350, 370, 390, 410, 430, 450]

    # tao man ket thuc
game_over_surface = pygame.image.load('assets/mess.png').convert_alpha()
game_over_rect = game_over_surface.get_rect(center = (216, 340))

    # chen am thanh
flap_sound = pygame.mixer.Sound('sound/sfx_wing.wav')
hit_sound = pygame.mixer.Sound('sound/sfx_hit.wav')
score_sound = pygame.mixer.Sound('sound/sfx_point.wav')
bullet_sound = pygame.mixer.Sound('sound/sound1.wav')
score_sound_countdown = 200

player = 1
    # dung vong lap game de hien thi game 
while True:
    for event in pygame.event.get():
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_1:
                bg = pygame.image.load('assets/worldcup.png').convert() # dung convert de load nhanh hon
            if event.key == pygame.K_2:
                bg = pygame.image.load('assets/quatar1.png').convert() # dung convert de load nhanh hon
            if event.key == pygame.K_3:
                bg = pygame.image.load('assets/quatar2.png').convert()
            if event.key == pygame.K_4:
                bg = pygame.image.load('assets/quatar3.png').convert()
            if event.key == pygame.K_5:
                bird_mid = pygame.transform.scale(maguire, (60,58)).convert_alpha()
                bird_list = [bird_mid]
                player = 1
            if event.key == pygame.K_6:
                bird_mid = pygame.transform.scale(messi, (60,59)).convert_alpha()
                bird_list = [bird_mid]
                player = 2
            if event.key == pygame.K_7:
                bird_mid = pygame.transform.scale(ronaldo, (60,59)).convert_alpha()
                bird_list = [bird_mid]
                player = 3
            if event.key == pygame.K_8:
                speedX = 3
            if event.key == pygame.K_9:
                speedX = 5
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE and game_active:
                bird_movement = 0
                bird_movement = -6
                flap_sound.play()
            if event.key == pygame.K_SPACE and game_active == False:
                game_active = True
                pipe_list.clear() # xoa het pipes da choi tu game truoc
                cup_list.clear()
                ufo_list.clear()
                bullet_list.clear()
                bird_rect.center = (100, 384)
                bird_movement = 0
        if event.type == spawnpipe: 
            if count > 30:
                pipe_surface = pygame.transform.scale2x(pygame.image.load('assets/cup.png')).convert_alpha()
                pipe_list.extend(create_pipe1())
            elif count > 20:
                pipe_surface = pygame.transform.scale2x(pygame.image.load('assets/pipe-gold.png')).convert_alpha()
                pipe_list.extend(create_pipe())
            elif count > 10:
                pipe_surface = pygame.transform.scale2x(pygame.image.load('assets/sword.png')).convert_alpha()
                pipe_list.extend(create_pipe1())
            else:
                pipe_surface = pygame.transform.scale2x(pygame.image.load('assets/pipe-gold.png')).convert_alpha()
                pipe_list.extend(create_pipe())
                    
        if event.type == spawncup:
            cup_list.append(create_cup())
            ufo_list.append(create_ufo())
            bullet_list.append(create_bullet())
        if event.type == bird_flap:
            bird, bird_rect = bird_animation()

    screen.blit(bg, (0,0)) # hien thi background 
    
    if game_active:
        collision = check_collision_cup(cup_list)
        collision1 = check_collision_ufo(ufo_list)
        collision2 = check_collision_bullet(bullet_list)
        # bird
        bird_movement += gravity # con chim di chuyen, trong luc cang tang
        rotated_bird = rotate_bird(bird)
        bird_rect.centery += bird_movement
        screen.blit(rotated_bird, bird_rect) # hien thi background 
        game_active = check_collision(pipe_list)

        # pipe
        pipe_list = move_pipe(pipe_list)
        draw_pipe(pipe_list)
        if collision == False: 
            score_sound.play()
            score += 1
            count += 2
            if count > 40: count = 0
        if collision1 == False: 
            score += 2
            count += 2
            if count > 40: count = 0
            score_sound.play()
        if collision2 == False: 
            red_card += 1
            score -= 1
            count -= 2
            if count > 40: count = 0
            # bullet_sound.play()
        #cup 
        cup_list = move_cup(cup_list)
        draw_cup(cup_list) 
        #ufo
        ufo_list = move_ufo(ufo_list)
        draw_ufo(ufo_list) 
        #bullet
        bullet_list = move_bullet(bullet_list)
        draw_bullet(bullet_list)

        score_display('main game')
    else:
        if player == 1:
            bird = pygame.transform.scale(maguire, (200, 200))
            screen.blit(bird, (116, 284))
        if player == 2:
            bird = pygame.transform.scale(messi, (200, 200))
            screen.blit(bird, (116, 284))
        if player == 3:
            bird = pygame.transform.scale(ronaldo, (200, 200))
            screen.blit(bird, (116, 284))
        # screen.blit(game_over_surface, game_over_rect)
        high_score = update_score(score, high_score)
        score_display('game_over') 
        score = 0
        red_card = 0
    # floor 
    floor_x_pos -= 1 
    draw_floor() # ve san
    if floor_x_pos <= -432:
        floor_x_pos = 0

    pygame.display.update()
    clock.tick(120) # de FPS cho game