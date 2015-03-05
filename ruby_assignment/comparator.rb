def compare(first_string,second_string)
	if first_string.eql?(second_string)
	   puts "Both string are equal"
	else
	   puts "Strings are unequal"
	end
end


puts "Enter first String"
first_string = gets.chomp
puts "Enter second String"
second_string = gets.chomp
compare(first_string,second_string)

