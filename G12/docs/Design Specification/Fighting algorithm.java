int MAX_RANGE = 30; 
public boolean fight(Male opponent) 
{
	float damageToOpponent = 0; 
	float damageToPlayer = 0; 
	do
	{
		damageToOpponent = (((this.speed + this.strength + this.accuracy)*3)-MAX_RANGE + random(MAX_RANGE*2)) - (((opponent.dodge + opponent.endurance + opponent.armor)/3)-MAX_RANGE + random(MAX_RANGE*2));
		if(damageToOpponent > oppent.health)
			return true; 
		damageToPlayer = (((opponent.speed + opponent.strength + opponent.accuracy)*3)-MAX_RANGE + random(MAX_RANGE*2)) - (((this.dodge + this.endurance + this.armor)/3)-MAX_RANGE + random(MAX_RANGE*2));
		if(damageToPlayer > player.health)
			return false; 
	} while(true);
}