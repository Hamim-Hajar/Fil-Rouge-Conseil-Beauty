
export class Article {
  id: number;
  titre: string;
  contenu: string;
  datePublication: Date; // Use Date for LocalDateTime equivalent
  image?: string; // Optional, as it is nullable in your entity
  specialistId: number; // Representing the relation with Specialist

  constructor(
    id: number,
    titre: string,
    contenu: string,
    datePublication: Date,
    image?: string,
    specialistId?: number
  ) {
    this.id = id;
    this.titre = titre;
    this.contenu = contenu;
    this.datePublication = datePublication;
    this.image = image || ''; // Optional handling
    this.specialistId = specialistId || 0; // Optional handling
  }
}
