public Monster[] breeding(Male monster)
{
	Random random = new Random(); 
	
	int numberOfChildren = Math.sqrt(fertility * monster.fertility) * MAX_CHILDREN; 
	Monster children[numberOfChildren + 1]; 
	
	for(int i = 0; i < numberOfChildren; i++)
	{
		boolean isMale = random.nextBoolean();
		if(isMale)
		{
			children[i] = new Male();
		}
		else
		{
			children[i] = new Female(); 
		}
		
		children[i].name = "New monster " + i; 
		children[i].dob = new Date();		
		if(random.nextInt(100)<5)
			children[i].strength = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].strength = strength; 
		else
			children[i].strength = monster.strength; 
			
		if(random.nextInt(100)<5)
			children[i].speed = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].speed = speed; 
		else
			children[i].speed = monster.speed; 
			
		if(random.nextInt(100)<5)
			children[i].accuracy = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].accuracy = accuracy; 
		else
			children[i].accuracy = monster.accuracy; 
			
		if(random.nextInt(100)<5)
			children[i].endurance = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].endurance = endurance; 
		else
			children[i].endurance = monster.endurance; 
			
		if(random.nextInt(100)<5)
			children[i].armor = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].armor = armor; 
		else
			children[i].armor = monster.armor; 
			
		if(random.nextInt(100)<5)
			children[i].dodge = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].dodge = dodge; 
		else
			children[i].dodge = monster.dodge; 
			
		if(random.nextInt(100)<5)
			children[i].age_rate = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].age_rate = age_rate; 
		else
			children[i].age_rate = monster.age_rate; 		
		if(random.nextInt(100)<5)
			children[i].fertility = random.nextFloat(1);
		else if(random.nextInt(100)<50)
			children[i].fertility = fertility; 
		else
			children[i].fertility = monster.fertility; 			
		children[i].health = 100; //?
	}
	return children; 
}