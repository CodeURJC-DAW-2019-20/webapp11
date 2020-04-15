export interface WebUser{
    idUser?:number;
    userPhoto?:string;
	userName:string;
	userDni:string;
	userAge:number;
	userAdress:string;
	userHouseSize:string;
	userGarden:string;
	userNumChildren:number;
	userNumPeopleInHouse:number;
	userEmail:string;
	userCapacity?:number;
	userPassword:string;
	role: string;
	authdata?: string;
}