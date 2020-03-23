import { Shelter } from '../Shelter/shelter.model';

export interface Animal{
	id:number;
    photo?:string;
	name:string;
	age:number;
	type:string;
	size:string;
	description:string;
	adopted:boolean;
	dimensions?:number;
    owner:Shelter;
}