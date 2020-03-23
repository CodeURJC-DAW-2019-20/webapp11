import {WebUser} from '../WebUser/webUser.model';

export interface UserGalleryPhoto{
    id:number;
    photo:string;
    owner?:WebUser;
}