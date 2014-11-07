JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = \
	pollaxmud/entities/Backpack.java \
	pollaxmud/entities/Book.java \
	pollaxmud/entities/Course.java \
	pollaxmud/entities/Creature.java \ 
	pollaxmud/entities/Item.java \
	pollaxmud/entities/Key.java \
	pollaxmud/entities/package-info.java \
	pollaxmud/entities/Player.java \
	pollaxmud/entities/Question.java \
	pollaxmud/entities/Sphinx.java \
	pollaxmud/entities/Teacher.java \
	pollaxmud/enums/CreatureType.java \
	pollaxmud/enums/Direction.java \
	pollaxmud/enums/ItemType.java \
	pollaxmud/enums/package-info.java \
	pollaxmud/exceptions/CustomException.java \
	pollaxmud/exceptions/package-info.java \
	pollaxmud/gui/MapCanvas.java \
	pollaxmud/gui/package-info.java \
	pollaxmud/handlers/ConversationHandler.java \
	pollaxmud/handlers/InputHandler.java \
	pollaxmud/handlers/package-info.java \
	pollaxmud/main/Pollaxmud.java \
	pollaxmud/utilities/Bookimporter.java \
	pollaxmud/utilities/CourseImporter.java \
	pollaxmud/utilities/package-info.java \
	pollaxmud/utilities/QuestionImporter.java \
	pollaxmud/utilities/TeacherImporter.java \
	pollaxmud/utilities/WorldImporter.java \
	pollaxmud/world/package-info.java \
	pollaxmud/world/Room.java \
	pollaxmud/world/World.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class