import { Shelter } from '../Shelter/shelter.model';

export interface Animal{
	idAnimal?:number;
    animalPhoto?:string;
	animalName:string;
	animalAge:number;
	animalType:string;
	animalSize:string;
	animalDescription:string;
//	adopted:boolean;
//	dimensions?:number;
//  owner:Shelter;
}