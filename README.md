# heads-and-hands-test-task
## Вступление
[![Typing SVG](https://readme-typing-svg.herokuapp.com?color=%2336BCF7&lines=HI+THERE!)](https://git.io/typing-svg)

Привет, меня зовут Глеб и я хочу попасть в школу разработчиков "Heads and hands". Здесь вы увидите моё тестовое задание.

Telegram для связи - @Gleb_Komelin

## Описание проекта

Классы лежат в src/main/java
src/main/java/Game - тут лежат классы.
src/main/java/Main - тут лежит класс Main, в котором есть метод main, который показывает примеры использования методов и классов из Game.

## Описание логики классов (почему все сделано именно так)

### Класс Creature
Основой сущности является абстрактный класс Creature, описывающий основную логику всех сущностей игры, т.е. у всех сущностей должны быть: параметр атаки, параметр защиты, параметр здоровья, параметр урона.
В данном классе есть protected сеттеры и геттеры для всех полей. Модификатор protected выбран из той логики, что эти методы должны быть использваны только внутри пакета Game, который и описывает всю логику взаимодействия существ, в пакете Main мы не должны иметь доступа к изменению объекта путем геттеров и сеттеров. Также вы можете заметить поля типа AttackModifier, DiceToss, RandomValue. О них будет сказано позже. 

Далее стоит поговорить про метод attack - основной способ взаимодействия двух существ. На вход ему дается другой объект типа Creature. Изначально проводится проверка на то, не пытается ли существо атаковать само себя, в случае одинаковой ссылки атакующего и защищающегося выбрасывается IllegalArgumentException. Далее следует проверка на то, является ли атакующий или защищающийся живыми, соответствующие сообщения выводятся в консоль. В случае выполнения всех условий, инициализируется boolean переменная isDiceSuccessful, являющаяся "флагом" того, был ли хотя бы один подброс кости успешным. Тут уже стоит поговорить про AttackModifier, DiceToss и RandomValue. Это интерфейсы, описивающие логику просчета модификатора атаки, подброса кубика и просчета случайного значения из диапазона. Их реализуют соответсвующие классы DefaultAttackModifier, SixEdgedDice и DefaultRandomValue. Зачем это было сделано? Это сделано для большей гибкости взаимодействия существ и возможным последующим расширением функционала. Предположим, что мы хотим добавить "босс" монстра, который игнорирует N-ое количество брони персонажа при подсчете модификатора атаки (условно, подсчет тот же, что и у дефолтного модификатора, только броня защищающегося умножается на некоторый понижающий коэфицент). В случае если мы прописываем всю логику подсчета модификатора атаки в самом методе attack, для создания "босс" монстра мы должны переопределить весь метод attack, при этом изменяя только реализацию подсчета модификатора атаки. В моей реализации мы просто должны создать новый класс, реализовать в нем интерфейс AttackModifier, реализовать в этом классе метод getAttackModifier и в классе "босс" монстра поставить этот новый модификатор атаки через метод setAttackModifier. Аналогичное объяснение для DiceToss и RandomValue. Допустим мы захотим, чтобы мы теперь бросали не 6, а 8 гранный кубик, а в определении урона, который нанесет существо, использовали не обычный, а "псевдо" рандом (такой рандом используется во многих механиках игр, в той же самой Dota 2 например :) ).

Про конструкторы класса Creature. Есть 2 конструктора: пустой и all-args. В пустом контрукторе поля иницализируются случайными значениями (в разумном для них пределе). В all-args, очевидно, вручную. При этом в all-args конструкторе происходит проверка на валидность данных, в случае неправильного заполнение будет выброщен IllegalArgumentException с соответсвующим сообщением. Стоит упомянуть про нестатический блок инициализации, в нем инициализируются дефолтными значениями поля типа AttackModifier, DiceToss и RandomValue методами setDefaultAttackModifier и т.д соответственно.

Также был добавлен метод showInfo, просто чтобы показать информацию о существе.

### Класс Person
Этот класс наследуется от Creature, расширяет его методом heal. У него есть поле healUsed, являющееся счетчиком использования "хилок". В методе heal через геттеры и сеттеры происходит логика исцеления, тут играет большую роль поле MAX_HP_VALUE, которое инициализируется еще в классе Creature значением поля HPValue. Герой не может получить значение HP большее, чем MAX_HP_VALUE. В методе рассматриваются сценарии, когда сам персонаж уже мёртв или он исчерпал лимит исцелений. 

### Класс Monster
Ничего особенного про этот класс не сказать, просто наследник от Creature.

### Интерфейс AttackModifier и класс DefaultAttackModifier
О причинах создания данного интерфейса и его реализациях сказано в  


