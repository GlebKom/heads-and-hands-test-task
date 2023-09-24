# heads-and-hands-test-task
## Вступление
[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=HI+THERE!)](https://git.io/typing-svg)

Привет, меня зовут Глеб и я хочу попасть в школу разработчиков "Heads and hands". Здесь вы увидите моё тестовое задание.

Telegram для связи - @Gleb_Komelin


<a name="proj_descr"><h2>Описание проекта</h2></a>

Классы лежат в src/main/java

src/main/java/Game - тут лежат классы существ и т.д.

src/main/java/Main - тут лежит класс Main, в котором есть метод main, который показывает примеры использования методов и классов из Game.

<a name="logic_descr"><h2>Описание логики классов (почему все сделано именно так)</h2></a>

[Класс Creature](#creature_descr)

[Класс Person](#person_descr)

[Класс Monster](#monster_descr)

[Интерфейс AttackModifier и класс DefaultAttackModifier](#attack_modifier_descr)

[Интерфейс RandomValue и класс DefaultRandomValue](#random_value_descr)

[Интерфейс DiceToss и класс SixEdgedDice](#dice_toss_descr)

<a name="creature_descr"><h3>Класс Creature</h3></a>

Основой для сущности является абстрактный класс Creature, описывающий основную логику всех сущностей игры, т.е. у всех сущностей должны быть: параметр атаки, параметр защиты, параметр здоровья, параметр урона.
В данном классе есть protected сеттеры и геттеры для всех полей. Модификатор *protected* выбран из той логики, что эти методы должны быть использваны только внутри пакета Game, который и описывает всю логику взаимодействия существ, в пакете Main мы не должны иметь доступа к изменению объекта путем геттеров и сеттеров. Также вы можете заметить поля типа **AttackModifier**, **DiceToss**, **RandomValue**. О них будет сказано позже. 

Далее стоит поговорить про метод *attack* - основной способ взаимодействия двух существ. На вход ему дается другой объект типа **Creature**. Изначально проводится проверка на то, не пытается ли существо атаковать само себя, в случае одинаковой ссылки атакующего и защищающегося выбрасывается IllegalArgumentException. Далее следует проверка на то, является ли атакующий или защищающийся живыми (здоровье больше 0), соответствующие сообщения выводятся в консоль. В случае выполнения всех условий, инициализируется boolean переменная *isDiceSuccessful*, являющаяся "флагом" того, был ли хотя бы один подброс кости успешным. Тут уже стоит поговорить про **AttackModifier, DiceToss** и **RandomValue**. Это интерфейсы, описивающие логику просчета модификатора атаки, подброса кубика и просчета случайного значения из диапазона. Их реализуют соответсвующие классы [DefaultAttackModifier](#attack_modifier_descr), [SixEdgedDice](#dice_toss_descr) и [DefaultRandomValue](#random_value_descr). 

Зачем это было сделано? Это сделано для большей гибкости взаимодействия существ и возможным последующим расширением функционала. Предположим, что мы хотим добавить "босс" монстра, который игнорирует N-ое количество брони персонажа при подсчете модификатора атаки (условно, подсчет тот же, что и у дефолтного модификатора, только броня защищающегося умножается на некоторый понижающий коэфицент). В случае если мы прописываем всю логику подсчета модификатора атаки в самом методе *attack*, для создания "босс" монстра мы должны переопределить весь метод *attack*, при этом изменяя только реализацию подсчета модификатора атаки. В моей реализации мы просто должны создать новый класс, реализовать в нем интерфейс **AttackModifier**, реализовать в этом классе метод *getAttackModifier* и в классе "босс" монстра поставить этот новый модификатор атаки через метод *setAttackModifier*. Аналогичное объяснение для DiceToss и RandomValue. Допустим мы захотим, чтобы мы теперь бросали не 6, а 8 гранный кубик, а в определении урона, который нанесет существо, использовали не обычный, а "псевдо" рандом (такой рандом используется во многих механиках игр, в той же самой Dota 2 например :) ).

Про конструкторы класса Creature. Есть 2 конструктора: пустой и all-args. В пустом контрукторе поля иницализируются случайными значениями (в разумном для них пределе). В all-args, очевидно, вручную. При этом в all-args конструкторе происходит проверка на валидность данных, в случае неправильного заполнение будет выброщен IllegalArgumentException с соответсвующим сообщением. Стоит упомянуть про нестатический блок инициализации, в нем инициализируются дефолтными значениями поля типа **AttackModifier, DiceToss** и **RandomValue** методами *setDefaultAttackModifier* и т.д соответственно.

Также был добавлен метод *showInfo*, просто чтобы показать информацию о существе.

<a name="person_descr"><h3>Класс Person</h3></a>

Этот класс наследуется от **Creature**, расширяет его методом *heal*. У него есть поле healUsed, являющееся счетчиком использования "хилок". В методе heal через геттеры и сеттеры происходит логика исцеления, тут играет большую роль поле MAX_HP_VALUE, которое инициализируется еще в классе Creature значением поля HPValue. Герой не может получить значение HP большее, чем MAX_HP_VALUE. В методе рассматриваются сценарии, когда сам персонаж уже мёртв или он исчерпал лимит исцелений. 

<a name="monster_descr"><h3>Класс Monster</h3></a>

Ничего особенного про этот класс не сказать, просто наследник от Creature.

<a name="attack_modifier_descr"><h3>Интерфейс AttackModifier и класс DefaultAttackModifier</h3></a>

О причинах создания данного интерфейса и его реализациях сказано в [Класс Person](#person_descr). Интерфейс **AttackModifier** имеет один метод *getAttackModifier*, возвращающий значение модификатора атаки (int), на вход получающий два объектa типа **Creature** атакующего и защищающегося соответственно. **DefaultAttackModifier** соответственно реализует этот интерфес исходя из логики, описанной в задании.

<a name="random_value_descr"><h3>Интерфейс RandomValue и класс DefaultRandomValue</h3></a>

Интерфейс **RandomValue** имеет один метод *getRandomValue*, принимающий значения начала и конца диапазона для выборки случайного числа, на выход дает одно случайное число из диапазона. **DefaultRandomValue** реализует этот интерфейс, "под капотом" имеет встроенный "java.util.Random".

<a name="dice_toss_descr"><h3>Интерфейс DiceToss и класс SixEdgedDice</h3></a>

Интерфейс **DiceToss** имеет один метод *toss*, который ничего не принимет и возвращает число. **SixEdgedDice** реализует этот интерфейс и реализует логику подброса игральной кости, имеет поле типа **RandomValue** проинициализированное **DefaultRandomValue**. Это поле участвует в логике подброса кубика.

## Заключение

Спасибо, если дочитали до этого момента. Спасибо за предоставленную возможность попробовать свои силы. Задание было интересным, мне понравилось его выполнять. Хорошего Вам дня!
