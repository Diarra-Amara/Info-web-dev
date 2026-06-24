import type { Task } from "@/types";
import api from "./axios";

export const taskService={
    getAll(){
        return api.get<Task[]>('/api/tasks')
    },
    create(task : Omit<Task,'id'>){
        {
            return api.post<Task>('/api/tasks',task)
        }
    },
     update ( id : number , task : Task ) {
     return api.put< Task >( `/api/tasks/${id}`, task )
     },
     delete ( id : number ) {
         return api.delete(`/api/tasks/${id}`)
     }
}