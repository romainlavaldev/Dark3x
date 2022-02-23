##
# Auteur Pierre Jacoboni
# Version 0.1 : Date : Wed Dec 19 15:38:33 CET 2018
#
require 'gtk3'

class Builder < Gtk::Builder

	def initialize 
	    super()
	    self.add_from_file(__FILE__.sub(".rb",".glade"))
		# Creation d'une variable d'instance par composant identifié dans glade
		puts "Création des variables d'instances"
		self.objects.each() { |p| 	
				unless p.builder_name.start_with?("___object") 
					puts "\tCreation de la variable d'instance @#{p.builder_name}"
					instance_variable_set("@#{p.builder_name}".intern, self[p.builder_name]) 
				end
		}
		@window1.show_all
		@window1.signal_connect('destroy') { puts "Au Revoir !!!"; Gtk.main_quit }
		# On connecte les signaux aux méthodes (qui doivent exister)
		puts "\nConnexion des signaux"
		self.connect_signals { |handler| 
				puts "\tConnection du signal #{handler}"
				begin
					method(handler) 
				rescue	
					puts "\t\t[Attention] Vous devez definir la methode #{handler} :\n\t\t\tdef #{handler}\n\t\t\t\t....\n\t\t\tend\n"
					self.class.send( :define_method, handler.intern) {
						puts "La methode #{handler} n'est pas encore définie.. Arrêt"
						Gtk.main_quit
					}
					retry
				end
		}	
	end

	# A partir d'ici on écrit le code
	
end

# On lance l'application
builder = Builder.new()
Gtk.main