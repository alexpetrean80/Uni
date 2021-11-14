import MovieProps from "./MovieProps";
import {IonItem, IonLabel, IonList} from "@ionic/react";
import React from "react";

interface MoviePropsExt extends MovieProps {
    onEdit: (id?: string) => void;
}

const Actor: React.FC<string> = (actorName: string) => {
    return (
        <IonItem>
            <IonLabel>{actorName}</IonLabel>
        </IonItem>
    )
}
const Movie: React.FC<MoviePropsExt> = ({id, title, description, cast, onEdit}) => {
    return (
        <IonItem onClick={() => onEdit(id)}>
            <IonLabel>{title}</IonLabel>
            <IonLabel>{description}</IonLabel>
            <IonList>
                {cast.map(a => Actor(a))}
            </IonList>
        </IonItem>
    )
}

export default Movie;