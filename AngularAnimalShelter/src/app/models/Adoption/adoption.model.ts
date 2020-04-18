import { Animal } from '../Animal/animal.model';
import { WebUser } from '../WebUser/webUser.model';

export interface Adoption{
    id?:number;
    inCourse:boolean;
	adoptionDate:Date;
    animal?:Animal;
    user?:WebUser;
}